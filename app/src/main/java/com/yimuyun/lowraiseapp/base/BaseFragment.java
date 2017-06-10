package com.yimuyun.lowraiseapp.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;

import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.di.component.DaggerFragmentComponent;
import com.yimuyun.lowraiseapp.di.component.FragmentComponent;
import com.yimuyun.lowraiseapp.di.module.FragmentModule;
import com.yimuyun.lowraiseapp.ui.LoginActivity;
import com.yimuyun.lowraiseapp.util.SnackbarUtil;
import com.yimuyun.lowraiseapp.util.ToastUtil;

import org.jsoup.helper.StringUtil;

import javax.inject.Inject;

/**
 * Created by pengwq on 2017/04/27.
 * MVP Fragment基类
 */
public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {

    @Inject
    protected T mPresenter;

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) mPresenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String msg) {
        if(StringUtil.isBlank(msg)){
            return;
        }
        SnackbarUtil.show(((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    @Override
    public void showErrorMsgToast(String msg) {
        ToastUtil.shortShow(msg);
    }

    @Override
    public void useNightMode(boolean isNight) {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void cancelDialog() {

    }

    @Override
    public void stateMain() {

    }
    @Override
    public void showViewMain(boolean isShowViewMain,String errorMsg){

    }
    @Override
    public void reload(){

    }

    @Override
    public void startLoginActivity() {

        Intent intent = new Intent(mContext, LoginActivity.class);
        startActivity(intent);
        if(mActivity!=null) {
            mActivity.finish();
            getActivity().finish();
        }
    }

    @Override
    public void showMsgDialog(String title, String msg) {
        if(StringUtil.isBlank(msg)){
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setInverseBackgroundForced(true);
        // builder.setCustomTitle();
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    protected abstract void initInject();
}