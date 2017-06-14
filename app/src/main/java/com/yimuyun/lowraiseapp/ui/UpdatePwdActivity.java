package com.yimuyun.lowraiseapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.base.RootActivity;
import com.yimuyun.lowraiseapp.base.contract.ForgetPasswordContract;
import com.yimuyun.lowraiseapp.presenter.ForgetPasswordPresenter;

import org.jsoup.helper.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/6/10 12:14
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */
public class UpdatePwdActivity extends RootActivity<ForgetPasswordPresenter> implements ForgetPasswordContract.View{

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    @BindView(R.id.et_new_password)
    EditText mEtNewPassword;
    @BindView(R.id.et_password)
    EditText mEtPassword;

    String mobile ;
    String code;
    @Override
    protected int getLayout() {
        return R.layout.activity_update_pwd;
    }


    @Override
    protected void initEventAndData() {
        setToolBar(mToolBar,"忘记密码");
        mobile = getIntent().getStringExtra("mobile");
        code = getIntent().getStringExtra("code");
    }

    @OnClick(R.id.btn_confirm)
    public void confirm(View v){
        String newPassword = mEtNewPassword.getText().toString();
        if(StringUtil.isBlank(newPassword)){
            mEtNewPassword.requestFocus();
            return;
        }
        String password = mEtPassword.getText().toString();
        if(StringUtil.isBlank(password)){
            mEtPassword.requestFocus();
            return;
        }
        if(!password.equals(newPassword)){
            showErrorMsg("两次密码不一致");
            mEtPassword.requestFocus();
            return;
        }

        stateLoading();
        mPresenter.forgetPassword(mobile,code,password);
    }



    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    public static void open(Context context, String mobile, String code){
        Intent intent = new Intent(context,UpdatePwdActivity.class);
        intent.putExtra("mobile",mobile);
        intent.putExtra("code",code);
        context.startActivity(intent);
    }

    @Override
    public void setCode(String code) {

    }

    @Override
    public void resetCode() {

    }

    @Override
    public void forgetPasswordSuccess() {

    }
}
