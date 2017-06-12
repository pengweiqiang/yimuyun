package com.yimuyun.lowraiseapp.ui.diagnosis;

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
import com.yimuyun.lowraiseapp.base.contract.diagnosis.DrugListContract;
import com.yimuyun.lowraiseapp.model.bean.DrugBean;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;
import com.yimuyun.lowraiseapp.presenter.DrugPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * @author will on 2017/6/10 22:41
 * @email pengweiqiang64@163.com
 * @description 药品列表
 * @Version
 */

public class DrugListActivity extends RootActivity<DrugPresenter> implements DrugListContract.View{

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.listview)
    ListView mListView;

    private long selectedId;

    long enterpriseId;
    String type;//药品类型1，免疫 2，消毒 3，诊疗

    DrugListAdapter drugListAdapter ;
    private List<DrugBean> drugBeanList;
    @Override
    protected int getLayout() {
        return R.layout.activity_feed_list;
    }


    public static void open(Activity activity, long selectedId,String type){
        Intent intent = new Intent(activity,DrugListActivity.class);
        intent.putExtra(Constants.ID,selectedId);
        intent.putExtra(Constants.TYPE,type);
        activity.startActivityForResult(intent,Constants.REQUEST_DRUG);
    }

    @Override
    protected void initEventAndData() {
        selectedId = getIntent().getLongExtra(Constants.ID,0);
        type = getIntent().getStringExtra(Constants.TYPE);
        setToolBar(mToolBar,"药品列表");
        drugListAdapter = new DrugListAdapter(mContext,drugBeanList,selectedId);

        mListView.setAdapter(drugListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DrugBean drugBean = drugBeanList.get(position);
                Intent intent = new Intent();
                intent.putExtra(Constants.SELECTED_DRUG,drugBean);
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
        enterpriseId = userInfo.getPersonnel().getEnterpriseId();
        stateLoading();
        mPresenter.getDrugList(String.valueOf(enterpriseId),type);
    }

    @Override
    public void setDrugList(List<DrugBean> drugList) {
        this.drugBeanList = drugList;
        drugListAdapter.setDatas(drugList);
    }

}