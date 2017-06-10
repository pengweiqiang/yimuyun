package com.yimuyun.lowraiseapp.ui;

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

    @OnClick(R.id.tv_logout)
    public void logout(View view){
        loginOut();
    }
}
