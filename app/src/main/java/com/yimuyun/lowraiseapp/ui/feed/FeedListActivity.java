package com.yimuyun.lowraiseapp.ui.feed;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.base.RootActivity;
import com.yimuyun.lowraiseapp.base.contract.feed.FeedListContract;
import com.yimuyun.lowraiseapp.model.bean.FeedBean;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;
import com.yimuyun.lowraiseapp.presenter.FeedListPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * @author will on 2017/6/10 22:41
 * @email pengweiqiang64@163.com
 * @description 饲料
 * @Version
 */

public class FeedListActivity extends RootActivity<FeedListPresenter> implements FeedListContract.View{

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.listview)
    ListView mListView;

    private long selectedId;

    String enterpriseId;

    FeedListAdapter feedListAdapter ;
    private List<FeedBean> feedLists;
    @Override
    protected int getLayout() {
        return R.layout.activity_feed_list;
    }


    public static void open(Activity activity, long selectedId){
        Intent intent = new Intent(activity,FeedListActivity.class);
        intent.putExtra(Constants.ID,selectedId);
        activity.startActivityForResult(intent,Constants.REQUEST_FEED_LIST);
    }

    @Override
    protected void initEventAndData() {
        selectedId = getIntent().getLongExtra(Constants.ID,0);
        setToolBar(mToolBar,"饲料");
        feedListAdapter = new FeedListAdapter(mContext,feedLists,selectedId);

        mListView.setAdapter(feedListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FeedBean feedBean = feedLists.get(position);
                Intent intent = new Intent();
                intent.putExtra(Constants.SELECTED_FEED,feedBean);
                setResult(RESULT_OK,intent);
                finish();

            }
        });
        stateLoading();
        mPresenter.getUserInfo(App.getInstance().getUserBeanInstance().getPhoneNumber());

    }




    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void setUserInfo(UserInfo userInfo) {
        enterpriseId = String.valueOf(userInfo.getPersonnel().getEnterpriseId());
        mPresenter.getFeedList(enterpriseId);
    }

    @Override
    public void setFeedList(List<FeedBean> feedList) {
        this.feedLists = feedList;
        feedListAdapter.setDatas(this.feedLists);
    }
}