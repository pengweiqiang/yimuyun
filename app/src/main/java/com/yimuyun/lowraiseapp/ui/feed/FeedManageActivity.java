package com.yimuyun.lowraiseapp.ui.feed;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.base.RootActivity;
import com.yimuyun.lowraiseapp.base.contract.feed.FeedContract;
import com.yimuyun.lowraiseapp.model.bean.FeedBean;
import com.yimuyun.lowraiseapp.presenter.FeedPresenter;

import org.jsoup.helper.StringUtil;

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
    @BindView(R.id.tv_feed_time)
    TextView mTvFeedTime;

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    private long selectedFeedId;

    private String equipmentIds, feedTime, grass;
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
                if(checkFeeding()){
                    stateLoading();
                    mPresenter.feeding(equipmentIds,String.valueOf(selectedFeedId),feedTime,grass);
                }
            }
        });
    }

    @OnClick(R.id.tv_feed_name)
    public void getFeedList(View view){
        FeedListActivity.open(FeedManageActivity.this,selectedFeedId);
    }

    @OnClick(R.id.btn_add_ear_tag)
    public void addEarTag(View view){
        equipmentIds = "123123";
    }

    private boolean checkFeeding(){
        boolean isPassed = false;
        if(selectedFeedId == 0){
            showErrorMsg("请选择饲料");
            return isPassed;
        }
        if(StringUtil.isBlank(grass)){
            showErrorMsg("请选择牧草");
            return isPassed;
        }

        if(StringUtil.isBlank(feedTime)){
            showErrorMsg("请选择饲养日期");
            return isPassed;
        }
        if(StringUtil.isBlank(equipmentIds)){
            showErrorMsg("请录入耳标");
            return isPassed;
        }
        isPassed = true;
        return isPassed;
    }



    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    @Override
    public void feedingSuccess() {
        //喂养成功
        equipmentIds = null;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null){
            return;
        }
        FeedBean feedBean = (FeedBean) data.getSerializableExtra(Constants.SELECTED_FEED);
        if(feedBean!=null){
            selectedFeedId = feedBean.getId();
            mTvFeedName.setText(feedBean.getName());
        }
    }
}
