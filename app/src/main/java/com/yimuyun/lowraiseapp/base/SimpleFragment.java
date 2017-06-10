package com.yimuyun.lowraiseapp.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.ui.LoginActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by pengwq on 16/8/11.
 * 无MVP的Fragment基类
 */

public abstract class SimpleFragment extends SupportFragment {

    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    private Unbinder mUnBinder;
    protected boolean isInited = false;
    protected int currentPage = 1;
    protected int pageSize = 10;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        Log.d("LayoutInflater000", "LayoutInflater-----LayoutInflater");
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
        if (savedInstanceState == null) {
            if (!isHidden()) {
                isInited = true;
                initEventAndData();
            }
        } else {
            if (!isSupportHidden()) {
                isInited = true;
                initEventAndData();
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!isInited && !hidden) {
            isInited = true;
            initEventAndData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }
    AlertDialog loginOutDialog = null;
    public void loginOut(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("注销");
        builder.setMessage("是否退出登陆");
        builder.setInverseBackgroundForced(true);
        // builder.setCustomTitle();
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                App.getInstance().setUserInstance(null);
                App.getAppComponent().preferencesHelper().setUserInstance(null);
                dialog.dismiss();
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        loginOutDialog = builder.create();
        loginOutDialog.show();
    }

    protected abstract int getLayoutId();

    protected abstract void initEventAndData();
}
