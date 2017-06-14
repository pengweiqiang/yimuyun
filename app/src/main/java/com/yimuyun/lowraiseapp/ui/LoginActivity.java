package com.yimuyun.lowraiseapp.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.base.RootActivity;
import com.yimuyun.lowraiseapp.base.contract.UserContract;
import com.yimuyun.lowraiseapp.presenter.UserPresenter;

import org.jsoup.helper.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/6/10 12:14
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */
public class LoginActivity extends RootActivity<UserPresenter> implements UserContract.View{

    @BindView(R.id.et_user_name)
    EditText mEtUserName;
    @BindView(R.id.et_password)
    EditText mEtPassword;

    @BindView(R.id.tv_forget_password)
    TextView mTvForgetPassword;

    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }


    @Override
    protected void initEventAndData() {

    }

    @OnClick(R.id.btn_login)
    public void btnLogin(View v){
        login();
    }

    @OnClick(R.id.tv_forget_password)
    public void forgetPassword(View v){
        Intent intent = new Intent(mContext,ForgetPasswordActivity.class);
        startActivity(intent);
    }

    private void login(){
        String userName = mEtUserName.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();
        if(StringUtil.isBlank(userName)){
            showErrorMsgToast("请输入账号");
            mEtUserName.requestFocus();
            return;
        }
        if(StringUtil.isBlank(password)){
            showErrorMsgToast("请输入密码");
            mEtPassword.requestFocus();
            return;
        }
        stateLoading();
        mPresenter.login(userName,password);

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(mContext,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
