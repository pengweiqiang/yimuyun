package com.yimuyun.lowraiseapp.ui;

import android.content.Intent;
import android.widget.ImageView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.base.BaseActivity;
import com.yimuyun.lowraiseapp.base.contract.home.WelcomeContract;
import com.yimuyun.lowraiseapp.presenter.home.WelcomePresenter;

import butterknife.BindView;

/**
 * @author will on 2017/5/3 18:18
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.View {

    @BindView(R.id.iv_welcome_bg)
    ImageView mIvWelcomeBg;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.startMainActivity();
    }


    @Override
    public void jumpToLogin() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void jumpToHome() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void showMsgDialog(String title, String msg) {

    }
}
