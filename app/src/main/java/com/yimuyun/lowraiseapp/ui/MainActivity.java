package com.yimuyun.lowraiseapp.ui;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.base.SimpleActivity;
import com.yimuyun.lowraiseapp.ui.diagnosis.DiagnosisManageActivity;
import com.yimuyun.lowraiseapp.ui.disinfect.DisinfectManageActivity;
import com.yimuyun.lowraiseapp.ui.environmentmonitor.EnvironmentMonitorActivity;
import com.yimuyun.lowraiseapp.ui.feed.FeedManageActivity;
import com.yimuyun.lowraiseapp.ui.fertilization.FertilizationManageActivity;
import com.yimuyun.lowraiseapp.ui.immune.ImmuneManageActivity;
import com.yimuyun.lowraiseapp.ui.innocuitydeal.InnocuityDealActivity;
import com.yimuyun.lowraiseapp.ui.neweartag.NewEarTagManageActivity;
import com.yimuyun.lowraiseapp.ui.offsale.OffSaleActivity;
import com.yimuyun.lowraiseapp.ui.purchase.PurchaseActivity;
import com.yimuyun.lowraiseapp.ui.quarantine.QuarantineActivity;
import com.yimuyun.lowraiseapp.ui.weight.WeightManageActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends SimpleActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.tv_logout)
    TextView mTvLogout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.dot_first)
    TextView mTvdotFirst;

    @BindView(R.id.dot_second)
    TextView mTvdotSecond;

    private List<View> viewList;//view数组
    private View functionView1,functionView2;

    private View mViewFeed,//饲养
            mViewWeight,//称重
            mViewImmune,//免疫
            mViewDisinfect,//消毒
            mViewDiagnosis,//诊疗
            mViewNewEarTag;//新建耳标

    private View mViewQuarantine,//检疫
            mViewFertilization,//受孕管理
            mViewInnocuityDeal,//无害化处理
            mViewOfflineSale,//线下销售
            mVewnvironmentMonitor,//环境监测
            mViewPurchase;//收购入栏


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        setToolBar(mToolBar, "概览菜单", R.mipmap.ic_close, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getInstance().exitApp();
            }
        });
        setToolBarTitleColor(mToolBar,R.color.color_main_tab_title);


        initViewPager();
    }


    private void initViewPager(){
        LayoutInflater inflater=getLayoutInflater();
        functionView1 = inflater.inflate(R.layout.item_main_function1, null);
        functionView2 = inflater.inflate(R.layout.item_main_function2, null);

        mViewFeed = functionView1.findViewById(R.id.rl_feed);
        mViewWeight = functionView1.findViewById(R.id.rl_weight);
        mViewDisinfect = functionView1.findViewById(R.id.rl_disinfect);
        mViewImmune = functionView1.findViewById(R.id.rl_immune);
        mViewDiagnosis = functionView1.findViewById(R.id.rl_diagnosis);
        mViewNewEarTag = functionView1.findViewById(R.id.rl_new_ear_tag);

        mViewQuarantine = functionView2.findViewById(R.id.rl_quarantine);
        mViewFertilization = functionView2.findViewById(R.id.rl_fertilization);
        mViewInnocuityDeal = functionView2.findViewById(R.id.rl_innocuity_deal);
        mViewOfflineSale = functionView2.findViewById(R.id.rl_offline_sale);
        mVewnvironmentMonitor = functionView2.findViewById(R.id.rl_environment_monitor);
        mViewPurchase = functionView2.findViewById(R.id.rl_purchase);

        mViewFeed.setOnClickListener(onClickListener);
        mViewWeight.setOnClickListener(onClickListener);
        mViewDisinfect.setOnClickListener(onClickListener);
        mViewImmune.setOnClickListener(onClickListener);
        mViewDiagnosis.setOnClickListener(onClickListener);
        mViewNewEarTag.setOnClickListener(onClickListener);

        mViewQuarantine.setOnClickListener(onClickListener);
        mViewFertilization.setOnClickListener(onClickListener);
        mViewInnocuityDeal.setOnClickListener(onClickListener);
        mViewOfflineSale.setOnClickListener(onClickListener);
        mVewnvironmentMonitor.setOnClickListener(onClickListener);
        mViewPurchase.setOnClickListener(onClickListener);


        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(functionView1);
        viewList.add(functionView2);

        PagerAdapter pagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewList.get(position));


                return viewList.get(position);
            }
        };

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    mTvdotFirst.setEnabled(true);
                    mTvdotSecond.setEnabled(false);
                }else if(position ==1){
                    mTvdotFirst.setEnabled(false);
                    mTvdotSecond.setEnabled(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setAdapter(pagerAdapter);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rl_feed:
                    startActivityForIntent(FeedManageActivity.class);
                    break;
                case R.id.rl_weight:
                    LongerGetEarTagActivity.open(mContext,WeightManageActivity.class.getName());
//                    startActivityForIntent(WeightManageActivity.class);
                    break;
                case R.id.rl_immune:
                    startActivityForIntent(ImmuneManageActivity.class);
                    break;
                case R.id.rl_disinfect:
                    startActivityForIntent(DisinfectManageActivity.class);
                    break;
                case R.id.rl_diagnosis:
                    LongerGetEarTagActivity.open(mContext,DiagnosisManageActivity.class.getName());
                    break;
                case R.id.rl_new_ear_tag:
                    LongerGetEarTagActivity.open(mContext,NewEarTagManageActivity.class.getName());
                    break;
                case R.id.rl_quarantine:
                    startActivityForIntent(QuarantineActivity.class);
//                    LongerGetEarTagActivity.open(mContext,OnlineSaleActivity.class.getName());
                    break;
                case R.id.rl_fertilization:
                    LongerGetEarTagActivity.open(mContext,FertilizationManageActivity.class.getName());
                    break;
                case R.id.rl_innocuity_deal:
                    LongerGetEarTagActivity.open(mContext, InnocuityDealActivity.class.getName());
                    break;
                case R.id.rl_offline_sale:
                    startActivityForIntent(OffSaleActivity.class);
                    break;
                case R.id.rl_environment_monitor:
                    startActivityForIntent(EnvironmentMonitorActivity.class);
                    break;
                case R.id.rl_purchase:
                    LongerGetEarTagActivity.open(mContext, PurchaseActivity.class.getName());
                    break;

            }
        }
    };
    private void startActivityForIntent(Class className){
        Intent intent = new Intent(mContext,className);
        startActivity(intent);
    }

    @OnClick(R.id.tv_logout)
    public void logout(View view){
        loginOut();
    }
}
