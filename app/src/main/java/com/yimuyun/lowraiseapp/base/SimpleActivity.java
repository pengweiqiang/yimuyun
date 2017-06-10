package com.yimuyun.lowraiseapp.base;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.ui.LoginActivity;

import org.jsoup.helper.StringUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by pengwq on 17/05/02.
 * 无MVP的activity基类
 */

public abstract class SimpleActivity extends SupportActivity {

    protected Activity mContext;
    private Unbinder mUnBinder;
    protected int currentPage = 1;
    protected int pageSize = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;
        onViewCreated();
        App.getInstance().addActivity(this);
        initEventAndData();
    }

    protected void setToolBar(Toolbar toolbar, String title) {
       setToolBar(toolbar, title,"",R.mipmap.ic_back, new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               onBackPressedSupport();
           }
       },null);
    }
    protected void setToolBar(Toolbar toolbar, String title, View.OnClickListener onClickListener) {
        setToolBar(toolbar,title,"",R.mipmap.ic_back,onClickListener,null);
    }

    protected void setToolBarTitleColor(Toolbar toolbar,int colorId){
        TextView mTvTitlte = (TextView) toolbar.findViewById(R.id.tv_header_title);
        mTvTitlte.setTextColor(getResources().getColor(colorId));
    }

    protected void setToolBar(Toolbar toolBar,String title,int drawableLogo,View.OnClickListener onClickListener){
        setToolBar(toolBar,title,"",drawableLogo,onClickListener,null);
    }

    protected void setToolBar(Toolbar toolbar, String title, String rightTitle,int drawableLogo, View.OnClickListener backOnclickListener, View.OnClickListener rightOnclickListener){
        toolbar.setTitle("");
        TextView mTvTitlte = (TextView) toolbar.findViewById(R.id.tv_header_title);
        TextView mTvRight = (TextView)toolbar.findViewById(R.id.tv_right);
        mTvTitlte.setText(title);
        if(StringUtil.isBlank(rightTitle)){
            mTvRight.setVisibility(View.GONE);
        }else{
            mTvRight.setText(rightTitle);
            mTvRight.setVisibility(View.VISIBLE);
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(drawableLogo);
        toolbar.setNavigationOnClickListener(backOnclickListener);
        mTvRight.setOnClickListener(rightOnclickListener);
    }

    protected void setToolBarTitle(Toolbar toolbar,String title,String rightTitle){
        TextView mTvTitlte = (TextView) toolbar.findViewById(R.id.tv_header_title);
        mTvTitlte.setText(title);

        TextView mTvRight = (TextView)toolbar.findViewById(R.id.tv_right);
        if(StringUtil.isBlank(rightTitle)) {
            mTvRight.setVisibility(View.GONE);
        }else{
            mTvRight.setVisibility(View.VISIBLE);
            mTvRight.setText(rightTitle);
        }
    }

    protected void onViewCreated() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(loginOutDialog!=null){
            loginOutDialog.dismiss();
            loginOutDialog = null;
        }
        App.getInstance().removeActivity(this);
        mUnBinder.unbind();
    }

    /**
     * 退出登陆
     */
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
                finish();
            }
        });
        loginOutDialog = builder.create();
        loginOutDialog.show();
    }

    protected abstract int getLayout();

    protected abstract void initEventAndData();
}
