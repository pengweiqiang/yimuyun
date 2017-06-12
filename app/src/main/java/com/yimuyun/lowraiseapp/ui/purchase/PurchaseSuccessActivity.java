package com.yimuyun.lowraiseapp.ui.purchase;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.base.SimpleActivity;
import com.yimuyun.lowraiseapp.ui.GetEarTagActivity;

/**
 * @author will on 2017/6/11 13:43
 * @email pengweiqiang64@163.com
 * @description 收购成功
 * @Version
 */

public class PurchaseSuccessActivity extends SimpleActivity{

    @Override
    protected int getLayout() {
        return R.layout.activity_purchase_success;
    }

    @Override
    protected void initEventAndData() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                GetEarTagActivity.open(mContext,PurchaseActivity.class.getName());
                finish();
            }
        },3000);
    }

    public static void open(Context context){
        Intent intent = new Intent(context,PurchaseSuccessActivity.class);
        context.startActivity(intent);
    }
}
