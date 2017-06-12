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
import com.yimuyun.lowraiseapp.base.contract.diagnosis.DiagnosisTreatmentPlanListContract;
import com.yimuyun.lowraiseapp.model.bean.DiagnosisTreatmentPlanBean;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;
import com.yimuyun.lowraiseapp.presenter.DiagnosisTreatmentPlanListPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * @author will on 2017/6/10 22:41
 * @email pengweiqiang64@163.com
 * @description 诊疗方案
 * @Version
 */

public class DiagnosisTreatmentPlanListActivity extends RootActivity<DiagnosisTreatmentPlanListPresenter> implements DiagnosisTreatmentPlanListContract.View{

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.listview)
    ListView mListView;

    private long selectedId;

    long enterpriseId;

    DiagnosisTreatmentPlanListAdapter diagnosisTreatmentPlanListAdapter ;
    private List<DiagnosisTreatmentPlanBean> diagnosisTreatmentPlanBeanList;
    @Override
    protected int getLayout() {
        return R.layout.activity_feed_list;
    }


    public static void open(Activity activity, long selectedId){
        Intent intent = new Intent(activity,DiagnosisTreatmentPlanListActivity.class);
        intent.putExtra(Constants.ID,selectedId);
        activity.startActivityForResult(intent,Constants.REQUEST_ADD_DIAGNOSIS_TREATMENT_PLAN);
    }

    @Override
    protected void initEventAndData() {
        selectedId = getIntent().getLongExtra(Constants.ID,0);
        setToolBar(mToolBar,"诊疗方案");
        diagnosisTreatmentPlanListAdapter = new DiagnosisTreatmentPlanListAdapter(mContext,diagnosisTreatmentPlanBeanList,selectedId);

        mListView.setAdapter(diagnosisTreatmentPlanListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DiagnosisTreatmentPlanBean diagnosisTreatmentPlanBean = diagnosisTreatmentPlanBeanList.get(position);
                Intent intent = new Intent();
                intent.putExtra(Constants.SELECTED_DIAGNOSIS_TREATMENT_PLAN,diagnosisTreatmentPlanBean);
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
        mPresenter.getDiagnosisTreatmentPlanList(String.valueOf(enterpriseId));
    }

    @Override
    public void setDiagnosisTreatmentPlanList(List<DiagnosisTreatmentPlanBean> diagnosisTreatmentPlanList) {
        this.diagnosisTreatmentPlanBeanList = diagnosisTreatmentPlanList;
        diagnosisTreatmentPlanListAdapter.setDatas(diagnosisTreatmentPlanBeanList);
    }


}