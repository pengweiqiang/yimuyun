package com.yimuyun.lowraiseapp.base;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.widget.LoadingDialog;

import org.jsoup.helper.StringUtil;


/**
 * @author: Est
 * @date: 2017/4/21
 * @desciption:
 */

public abstract class RootFragment<T extends BasePresenter> extends BaseFragment<T> {

    private static final int STATE_MAIN = 0x00;
    private static final int STATE_LOADING = 0x01;
    private static final int STATE_ERROR = 0x02;

    private LoadingDialog loadingDialog;

    //    private ProgressImageView ivLoading;
    private LinearLayout viewError;
    //    private RelativeLayout viewLoading;
    private ViewGroup viewMain;
    private int currentState = STATE_MAIN;

    @Override
    protected void initEventAndData() {
        if (getView() == null)
            return;
        viewMain = (ViewGroup) getView().findViewById(R.id.view_main);
        if (viewMain == null) {
            throw new IllegalStateException(
                    "The subclass of RootActivity must contain a View named view_main.");
        }
        if (!(viewMain.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException(
                    "view_main's ParentView should be a ViewGroup.");
        }
        ViewGroup parent = (ViewGroup) viewMain.getParent();
        View.inflate(mContext, R.layout.view_error, parent);
//        View.inflate(mContext, R.layout.view_progress, parent);
        viewError = (LinearLayout) parent.findViewById(R.id.view_error);
//        viewLoading = (RelativeLayout) parent.findViewById(R.id.view_loading);
//        ivLoading = (ProgressImageView) viewLoading.findViewById(R.id.progress_bar);
        viewError.setVisibility(View.GONE);
//        viewLoading.setVisibility(View.GONE);
        viewMain.setVisibility(View.VISIBLE);
    }

    @Override
    public void stateError() {
        if (currentState == STATE_ERROR)
            return;
        hideCurrentView();
        currentState = STATE_ERROR;
        if (viewError != null)
            viewError.setVisibility(View.VISIBLE);
    }

    @Override
    public void stateLoading() {
        if (currentState == STATE_LOADING)
            return;
        hideCurrentView();
        currentState = STATE_LOADING;
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(mContext, R.style.LoadingDialog);
        }
        loadingDialog.show();
//        viewLoading.setVisibility(View.VISIBLE);
//        ivLoading.start();
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
        if (loadingDialog != null) {
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
//        viewMain.setVisibility(View.VISIBLE);
    }

    @Override
    public void cancelDialog() {
        if(loadingDialog!=null &&loadingDialog.isShowing()){
            loadingDialog.cancel();
        }
    }

    @Override
    public void showMsgDialog(String title, String msg) {
        if (StringUtil.isBlank(msg)) {
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

    private void hideCurrentView() {
        switch (currentState) {
            case STATE_MAIN:
//                viewMain.setVisibility(View.GONE);
                break;
            case STATE_LOADING:
                if (loadingDialog != null) {
                    loadingDialog.cancel();
                }
//                ivLoading.stop();
//                viewLoading.setVisibility(View.GONE);
                break;
            case STATE_ERROR:
                if (loadingDialog != null) {
                    loadingDialog.cancel();
                }
                if (viewError != null)
                    viewError.setVisibility(View.GONE);
                break;
        }
    }
}
