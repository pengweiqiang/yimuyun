package com.yimuyun.lowraiseapp.ui.purchase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.base.SimpleActivity;
import com.yimuyun.lowraiseapp.ui.LowGetEarTagActivity;

import java.lang.ref.WeakReference;

/**
 * @author will on 2017/6/11 13:43
 * @email pengweiqiang64@163.com
 * @description 收购成功
 * @Version
 */

public class PurchaseSuccessActivity extends SimpleActivity{

    MyHandler myHandler;
    @Override
    protected int getLayout() {
        return R.layout.activity_purchase_success;
    }

    @Override
    protected void initEventAndData() {
        myHandler = new MyHandler(mContext);
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LowGetEarTagActivity.open(mContext,PurchaseActivity.class.getName());
                finish();
            }
        },3000);
    }

    private static class MyHandler extends Handler{
        WeakReference<Activity> activityWeakReference ;
        public MyHandler(Activity activity){
            activityWeakReference = new WeakReference<Activity>(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }


    public static void open(Context context){
        Intent intent = new Intent(context,PurchaseSuccessActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(myHandler!=null) {
            myHandler.removeCallbacksAndMessages(null);
        }
        myHandler = null;
    }
}
