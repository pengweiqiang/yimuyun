package com.yimuyun.lowraiseapp.ui.neweartag;

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
 * @description 新建耳标管理
 * @Version
 */
public class NewEarTagManageActivity extends RootActivity<WeightPresenter> implements WeightContract.View{

    @BindView(R.id.tv_feed_name)
    TextView mTvFeedName;

    private String enterpriseId = "1";
    @Override
    protected int getLayout() {
        return R.layout.activity_feed_manage;
    }


    @Override
    protected void initEventAndData() {

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
