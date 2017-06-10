package com.yimuyun.lowraiseapp.ui.feed;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.base.RootActivity;
import com.yimuyun.lowraiseapp.base.contract.feed.FeedContract;
import com.yimuyun.lowraiseapp.model.bean.FeedBean;
import com.yimuyun.lowraiseapp.presenter.FeedPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/6/10 12:14
 * @email pengweiqiang64@163.com
 * @description 饲养管理
 * @Version
 */
public class FeedManageActivity extends RootActivity<FeedPresenter> implements FeedContract.View{

    @BindView(R.id.tv_feed_name)
    TextView mTvFeedName;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    private String enterpriseId = "1";

    private String equipmentIds, feedId, feedTime, grass;
    @Override
    protected int getLayout() {
        return R.layout.activity_feed_manage;
    }


    @Override
    protected void initEventAndData() {
        setToolBar(mToolBar, "喂养管理", "提交", R.mipmap.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateLoading();
                mPresenter.feeding(equipmentIds,feedId,feedTime,grass);
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

    @Override
    public void feedingSuccess() {
        //喂养成功
    }
}
