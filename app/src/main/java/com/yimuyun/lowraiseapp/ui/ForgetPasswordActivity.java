package com.yimuyun.lowraiseapp.ui;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.base.RootActivity;
import com.yimuyun.lowraiseapp.base.contract.ForgetPasswordContract;
import com.yimuyun.lowraiseapp.presenter.ForgetPasswordPresenter;

import butterknife.BindView;

/**
 * @author will on 2017/6/13 22:42
 * @email pengweiqiang64@163.com
 * @description 忘记密码
 * @Version
 */

public class ForgetPasswordActivity extends RootActivity<ForgetPasswordPresenter> implements ForgetPasswordContract.View{
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
        return R.layout.activity_forget_password;
    }


    @Override
    protected void initEventAndData() {

    }
    



    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    @Override
    public void setCode(String code) {

    }

    @Override
    public void forgetPasswordSuccess() {

    }
}
