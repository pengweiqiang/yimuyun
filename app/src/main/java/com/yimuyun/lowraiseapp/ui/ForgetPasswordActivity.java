package com.yimuyun.lowraiseapp.ui;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.base.RootActivity;
import com.yimuyun.lowraiseapp.base.contract.ForgetPasswordContract;
import com.yimuyun.lowraiseapp.presenter.ForgetPasswordPresenter;

import org.jsoup.helper.StringUtil;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/6/13 22:42
 * @email pengweiqiang64@163.com
 * @description 忘记密码
 * @Version
 */

public class ForgetPasswordActivity extends RootActivity<ForgetPasswordPresenter> implements ForgetPasswordContract.View {
    @BindView(R.id.et_user_name)
    EditText mEtUserName;

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    @BindView(R.id.btn_confirm)
    Button mBtnNext;
    @BindView(R.id.btn_get_code)
    Button xinget_code;
    @BindView(R.id.et_code)
    EditText mEtCode;
    private String code="";
    private String phone = "";

    private MyHandler handler;

    @Override
    protected int getLayout() {
        return R.layout.activity_forget_password;
    }

    private Timer timer;// 计时器
    private int time = 60;// 倒计时120秒

    private void regainCode() {
        time = 60;
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.sendEmptyMessage(time--);
            }
        }, 0, 1000);
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolBar,"忘记密码");
        handler = new MyHandler(this);
    }


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    @Override
    public void setCode(String code) {
        this.code = code;
        xinget_code.setEnabled(true);
    }

    @Override
    public void forgetPasswordSuccess() {

    }

    @OnClick(R.id.btn_confirm)
    public void confirm(){
        if(StringUtil.isBlank(code)){
            mEtUserName.requestFocus();
            showErrorMsg("请先获取验证码");
            return;
        }
        String userCode = mEtCode.getText().toString().trim();
        if(StringUtil.isBlank(userCode)){
            mEtCode.requestFocus();
            return;
        }
        if(!userCode.equals(code)){
            mEtCode.requestFocus();
            showErrorMsgToast("验证码输入错误");
            return;
        }
        UpdatePwdActivity.open(mContext,phone,code);
        finish();
    }
    @Override
    public void resetCode(){
        handler.sendEmptyMessage(0);
    }


    @OnClick(R.id.btn_get_code)
    public void getCode(){
        phone = mEtUserName.getText().toString().trim();
        if(StringUtil.isBlank(phone)){
            mEtUserName.requestFocus();
            return;
        }
        stateLoading();
        regainCode();
        xinget_code.setEnabled(false);
        mPresenter.getCode(phone);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(handler!=null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(timer!=null) {
            timer.cancel();
        }
    }

     static class MyHandler extends Handler {
        private WeakReference<Activity> weakReference;
        public MyHandler(Activity activity){
            weakReference = new WeakReference<Activity>(activity);
        }
        public void handleMessage(android.os.Message msg) {
            ForgetPasswordActivity forgetPasswordActivity = (ForgetPasswordActivity) weakReference.get();
            if(forgetPasswordActivity==null){
                return;
            }
            if (msg.what == 0) {
                forgetPasswordActivity.xinget_code.setEnabled(true);
                forgetPasswordActivity.xinget_code.setText("获取验证码");
                forgetPasswordActivity.timer.cancel();
            } else {
                if(forgetPasswordActivity.xinget_code!=null) {
                    forgetPasswordActivity.xinget_code.setText("剩余:" + msg.what + "秒");
                }
            }
        }


    };

}
