package com.yimuyun.lowraiseapp.base;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.widget.LoadingDialog;

import org.jsoup.helper.StringUtil;


/**
 * @date: 2017/4/21
 * @desciption:
 */

public abstract class RootActivity<T extends BasePresenter> extends BaseActivity<T> implements View.OnClickListener{

    private static final int STATE_MAIN = 0x00;
    private static final int STATE_LOADING = 0x01;
    private static final int STATE_ERROR = 0x02;

    //    private ProgressBar ivLoading;
    private LinearLayout viewError;
    private Button mBtnBack,mBtnReload;
    private TextView mTvErrorTips;
    //    private RelativeLayout viewLoading;
    private ViewGroup viewMain;
    private int currentState = STATE_MAIN;

    LoadingDialog loadingDialog;

    @Override
    protected void initEventAndData() {
        viewMain = (ViewGroup) findViewById(R.id.view_main);
        if (viewMain == null) {
            throw new IllegalStateException(
                    "The subclass of RootActivity must contain a View named view_main.");
        }
        if (!(viewMain.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException(
                    "view_main's ParentView should be a ViewGroup");
        }
        ViewGroup parent = (ViewGroup) viewMain.getParent();
        View.inflate(mContext, R.layout.view_error, parent);
//        View.inflate(mContext, R.layout.view_progress, parent);
        viewError = (LinearLayout) parent.findViewById(R.id.view_error);
        initErrorViewListener();
//        viewLoading = (RelativeLayout) parent.findViewById(R.id.view_loading);
//        ivLoading = (ProgressBar) viewLoading.findViewById(R.id.progress_bar);
        viewError.setVisibility(View.GONE);
//        viewLoading.setVisibility(View.GONE);
        viewMain.setVisibility(View.VISIBLE);
    }

    private void initErrorViewListener(){
        mBtnBack = (Button) viewError.findViewById(R.id.btn_back);
        mBtnReload = (Button) viewError.findViewById(R.id.btn_reload);
        mTvErrorTips = (TextView)viewError.findViewById(R.id.tv_error_tips);
        mBtnBack.setOnClickListener(this);
        mBtnReload.setOnClickListener(this);
    }

    @Override
    public void stateError() {
        if (currentState == STATE_ERROR)
            return;
        hideCurrentView();
        currentState = STATE_ERROR;
        if(viewMain!=null)
            viewMain.setVisibility(View.GONE);
        if (viewError != null)
            viewError.setVisibility(View.VISIBLE);
    }

    public void stateHideViewMain(){
        if (currentState == STATE_LOADING)
            return;
        if(viewMain!=null)
            viewMain.setVisibility(View.GONE);
    }

    @Override
    public void stateLoading() {
        if (currentState == STATE_LOADING)
            return;
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this, R.style.LoadingDialog);
        }
        hideCurrentView();
        currentState = STATE_LOADING;
//        viewLoading.setVisibility(View.VISIBLE);
//        ivLoading.show();
        loadingDialog.show();
        if(viewError!=null){
            viewError.setVisibility(View.GONE);
        }
    }

    @Override
    public void cancelDialog() {
        super.cancelDialog();
        currentState = STATE_MAIN;
        if(loadingDialog!=null && loadingDialog.isShowing()){
            loadingDialog.cancel();
        }
    }

    @Override
    public void stateMain() {
        if (currentState == STATE_MAIN)
            return;
        if (loadingDialog != null) {
            loadingDialog.cancel();
        }
        hideCurrentView();
        currentState = STATE_MAIN;
        if (viewMain != null) {
            viewMain.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showViewMain(boolean isShowViewMain,String errorMsg) {
        if(viewMain!=null) {
            if (isShowViewMain) {
                if(viewError!=null)
                    viewError.setVisibility(View.GONE);
                viewMain.setVisibility(View.VISIBLE);
            } else {
                viewMain.setVisibility(View.GONE);
            }
        }
        if(mTvErrorTips!=null && !StringUtil.isBlank(errorMsg)) {
            String tips = getResources().getString(R.string.error_tips);
            mTvErrorTips.setText(tips+"\n"+errorMsg);
        }
        if(loadingDialog!=null){
            loadingDialog.cancel();
        }
    }

    @Override
    public void showMsgDialog(String title, String msg) {
        if (StringUtil.isBlank(msg)) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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

    private void hideCurrentView() {
        switch (currentState) {
            case STATE_MAIN:
//                if(viewMain!=null)
//                    viewMain.setVisibility(View.GONE);
                break;
            case STATE_LOADING:
//                ivLoading.hide();
                if (loadingDialog != null) {
                    loadingDialog.cancel();
                }
//                viewLoading.setVisibility(View.GONE);
                break;
            case STATE_ERROR:
                if(viewMain!=null)
                    viewMain.setVisibility(View.GONE);
                if (viewError != null)
                        viewError.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                onBackPressedSupport();
                break;
            case R.id.btn_reload:
                reload();
                break;
        }
    }
}