package com.yimuyun.lowraiseapp.base;

import android.content.Intent;
import android.support.v7.app.AppCompatDelegate;
import android.view.ViewGroup;

import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.di.component.ActivityComponent;
import com.yimuyun.lowraiseapp.di.component.DaggerActivityComponent;
import com.yimuyun.lowraiseapp.di.module.ActivityModule;
import com.yimuyun.lowraiseapp.model.bean.UserBean;
import com.yimuyun.lowraiseapp.ui.LoginActivity;
import com.yimuyun.lowraiseapp.util.SnackbarUtil;
import com.yimuyun.lowraiseapp.util.ToastUtil;

import org.jsoup.helper.StringUtil;

import javax.inject.Inject;

/**
 * Created by pengwq on 2017/04/27.
 * MVP activity基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView {

    @Inject
    protected T mPresenter;

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showErrorMsg(String msg) {
        if(StringUtil.isBlank(msg)){
            return;
        }
        SnackbarUtil.showShort(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    @Override
    public void showErrorMsgToast(String msg) {
        ToastUtil.shortShow(msg);
    }

    @Override
    public void useNightMode(boolean isNight) {
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }

    @Override
    public void stateError() {
    }

    @Override
    public void stateLoading() {
    }

    @Override
    public void stateLoading(String msg) {

    }
    @Override
    public void reload(){

    }

    @Override
    public void stateMain() {
    }

    @Override
    public void cancelDialog() {
    }

    @Override
    public void showViewMain(boolean isShowViewMain,String errorMsg){
    }


    @Override
    public void startLoginActivity() {
        //清空登陆数据
        UserBean userBean = App.getInstance().getUserBeanInstance();
        String userName = "";
        if(userBean!=null) {
            userName = userBean.getPhoneNumber();
        }
        App.getInstance().setUserInstance(null);
        App.getAppComponent().preferencesHelper().setUserInstance(null);
        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.putExtra(Constants.USER_NAME,userName);
        mContext.startActivity(intent);
        finish();
    }

    protected abstract void initInject();
}