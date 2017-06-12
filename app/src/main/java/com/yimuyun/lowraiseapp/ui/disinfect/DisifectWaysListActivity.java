package com.yimuyun.lowraiseapp.ui.disinfect;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.base.RootActivity;
import com.yimuyun.lowraiseapp.base.contract.disinfect.DisinfectWayContract;
import com.yimuyun.lowraiseapp.presenter.DisinfectWayPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * @author will on 2017/6/10 22:41
 * @email pengweiqiang64@163.com
 * @description 消毒方法
 * @Version
 */

public class DisifectWaysListActivity extends RootActivity<DisinfectWayPresenter> implements DisinfectWayContract.View{

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.listview)
    ListView mListView;

    private String selectedName;


    DisinfectWayListAdapter disinfectWayListAdapter ;
    private List<String> disinfectWayList;
    @Override
    protected int getLayout() {
        return R.layout.activity_feed_list;
    }


    public static void open(Activity activity, String selectedName){
        Intent intent = new Intent(activity,DisifectWaysListActivity.class);
        intent.putExtra(Constants.SELECTED_DISINFECT_NAME,selectedName);
        activity.startActivityForResult(intent,Constants.REQUEST_DISINFECT);
    }

    @Override
    protected void initEventAndData() {
        selectedName = getIntent().getStringExtra(Constants.SELECTED_DISINFECT_NAME);
        setToolBar(mToolBar,"消毒方法");
        disinfectWayListAdapter = new DisinfectWayListAdapter(mContext,disinfectWayList,selectedName);

        mListView.setAdapter(disinfectWayListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemName = disinfectWayList.get(position);
                Intent intent = new Intent();
                intent.putExtra(Constants.SELECTED_DISINFECT_NAME,itemName);
                setResult(RESULT_OK,intent);
                finish();

            }
        });
        mPresenter.getDisifectWayList();

    }




    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }



    @Override
    public void setDisifectWayList(List<String> disifectWayList) {
        this.disinfectWayList = disifectWayList;
        disinfectWayListAdapter.setDatas(disifectWayList);
    }
}