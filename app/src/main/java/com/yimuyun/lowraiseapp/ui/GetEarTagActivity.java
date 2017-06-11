package com.yimuyun.lowraiseapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.base.SimpleActivity;

/**
 * @author will on 2017/6/11 13:43
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class GetEarTagActivity extends SimpleActivity{

    public static final String FROM = "from";

    private String className = "";
    @Override
    protected int getLayout() {
        return R.layout.activity_loading_get_ear_tag;
    }

    @Override
    protected void initEventAndData() {
        className = getIntent().getStringExtra(FROM);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.putExtra(Constants.EQUPIMENT_ID,getEquipmentId());
                intent.setClassName(mContext,className);
                startActivity(intent);
                finish();
            }
        },2000);
    }

    private String getEquipmentId(){
        //TODO 读卡获取（高频、低频）
        return "2";
    }

    public static void open(Context context, String className){
        Intent intent = new Intent(context,GetEarTagActivity.class);
        intent.putExtra(FROM,className);
        context.startActivity(intent);
    }
}
