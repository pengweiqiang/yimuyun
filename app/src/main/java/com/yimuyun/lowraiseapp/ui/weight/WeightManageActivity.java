package com.yimuyun.lowraiseapp.ui.weight;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.base.RootActivity;
import com.yimuyun.lowraiseapp.base.contract.weight.WeightContract;
import com.yimuyun.lowraiseapp.model.bean.FeedBean;
import com.yimuyun.lowraiseapp.presenter.WeightPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/6/10 12:14
 * @email pengweiqiang64@163.com
 * @description 称重管理
 * @Version
 */
public class WeightManageActivity extends RootActivity<WeightPresenter> implements WeightContract.View{

    @BindView(R.id.tv_feed_name)
    TextView mTvFeedName;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    private String enterpriseId = "1";
    @Override
    protected int getLayout() {
        return R.layout.activity_feed_manage;
    }


    @Override
    protected void initEventAndData() {
        setToolBar(mToolBar, "称重管理", "提交", R.mipmap.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateLoading();

            }
        });
    }

    @OnClick(R.id.tv_feed_name)
    public void getFeedList(View view){
        stateLoading();
        mPresenter.getFeedList(enterpriseId);
    }



    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void setFeedList(List<FeedBean> feedList) {

    }
}
