package com.yimuyun.lowraiseapp.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.yimuyun.lowraiseapp.R;

/**
 * @author will on 2017/6/11 12:50
 * @email pengweiqiang64@163.com
 * @description 消息提示确认框
 * @Version
 */

public class MsgAlertDialog extends AlertDialog{

    private TextView mTvMsg;
    private TextView mBtnCancel;
    private TextView mBtnConfirm;
    public MsgAlertDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_show_msg_layout);
        mTvMsg = (TextView) findViewById(R.id.tv_msg);
        mBtnCancel = (TextView)findViewById(R.id.tv_cancel);
        mBtnConfirm = (TextView)findViewById(R.id.tv_confirm);
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void setMsgText(String title){
        mTvMsg.setText(title);
    }
    public void setConfirmOnclickListener(View.OnClickListener onclickListener){
        mBtnConfirm.setOnClickListener(onclickListener);
    }

    public void setCancelText(String btnText, View.OnClickListener onClickListener){
        mBtnCancel.setText(btnText);
        mBtnCancel.setOnClickListener(onClickListener);
    }
    public void setConfirmOnclickListener(String btnText,View.OnClickListener onclickListener){
        mBtnConfirm.setText(btnText);
        mBtnConfirm.setOnClickListener(onclickListener);
    }

    protected MsgAlertDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected MsgAlertDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }



}
