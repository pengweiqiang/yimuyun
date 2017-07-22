package com.yimuyun.lowraiseapp.ui.disinfect;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.base.RootActivity;
import com.yimuyun.lowraiseapp.base.contract.disinfect.DisinfectContract;
import com.yimuyun.lowraiseapp.model.bean.DrugBean;
import com.yimuyun.lowraiseapp.model.bean.Personnel;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;
import com.yimuyun.lowraiseapp.presenter.DisinfectPresenter;
import com.yimuyun.lowraiseapp.ui.UserListActivity;
import com.yimuyun.lowraiseapp.ui.diagnosis.DrugListActivity;
import com.yimuyun.lowraiseapp.util.DateUtil;
import com.yimuyun.lowraiseapp.widget.TimeSelector;

import org.jsoup.helper.StringUtil;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/6/10 12:14
 * @email pengweiqiang64@163.com
 * @description 消毒管理
 * @Version
 */
public class DisinfectManageActivity extends RootActivity<DisinfectPresenter> implements DisinfectContract.View{

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    @BindView(R.id.tv_drug_name)
    TextView mTvDrugName;
    @BindView(R.id.tv_disinfect_time)
    TextView mTvDisinfectTime;
    @BindView(R.id.tv_disinfect_way)
    TextView mTvDisinfectWay;
    @BindView(R.id.tv_diagnosis_personnel)
    TextView mTvPersonnel;

    private String time;
    long dragId;
    long personnelId;
    private String disinfectantName;//消毒药名称
    private String disinfectWay;//消毒方法
    String enterpriseId;

    TimeSelector timeSelector;
    @Override
    protected int getLayout() {
        return R.layout.activity_disinfect;
    }


    @Override
    protected void initEventAndData() {
        setToolBar(mToolBar, "消毒管理", "提交", R.mipmap.ic_left_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()) {
                    stateLoading();
                    mPresenter.insertDisinfection(disinfectantName,time,disinfectWay,enterpriseId,String.valueOf(personnelId));
                }
            }
        });

        timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String showTime,String paramTime) {
                time = paramTime;
                mTvDisinfectTime.setText(showTime);
            }
        }, DateUtil.formatBeforeDate(new Date(),"yyyy-MM-dd",-30), "2099-12-31");
        stateLoading();
        mPresenter.getUserInfo(App.getInstance().getUserBeanInstance().getPhoneNumber());
    }

    @OnClick(R.id.tv_disinfect_time)
    public void selectDate(View view){
        timeSelector.show();
    }

    @OnClick(R.id.tv_diagnosis_personnel)
    public void selectPersonnal(View view){
        UserListActivity.open(DisinfectManageActivity.this,"消毒人员");
    }

    private boolean checkInput(){
        boolean isPassed = false;
        if(StringUtil.isBlank(enterpriseId)){
            stateLoading();
            mPresenter.getUserInfo(App.getInstance().getUserBeanInstance().getPhoneNumber());
            return false;
        }
        if(dragId == 0 ){
            showErrorMsg("请选择药品");
            return false;
        }
        if(StringUtil.isBlank(time)){
            showErrorMsg("请选择喂养日期");
            return false;
        }
        if(StringUtil.isBlank(disinfectWay)){
            showErrorMsg("请选择消毒方法");
            return false;
        }
        if(personnelId == 0){
            showErrorMsg("请选择消毒人员");
            return false;
        }

        isPassed = true;
        return isPassed;
    }


    @OnClick(R.id.tv_drug_name)
    public void selectDrug(View view){
        DrugListActivity.open(DisinfectManageActivity.this,dragId,"2");
    }

    @OnClick(R.id.tv_disinfect_way)
    public void selectDisinfectWay(View view){
        DisifectWaysListActivity.open(DisinfectManageActivity.this,disinfectWay);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null){
            return;
        }
        switch (requestCode){
            case Constants.REQUEST_DISINFECT://消毒方法
                disinfectWay =  data.getStringExtra(Constants.SELECTED_DISINFECT_NAME);
                mTvDisinfectWay.setText(disinfectWay);
                break;
            case Constants.REQUEST_DRUG://药品
                DrugBean drugBean = (DrugBean) data.getSerializableExtra(Constants.SELECTED_DRUG);
                dragId = drugBean.getId();
                disinfectantName = drugBean.getDrugName();
                mTvDrugName.setText(disinfectantName);
                break;
            case Constants.REQUEST_USER_ITEM://选择人员
                Personnel personnel = (Personnel) data.getSerializableExtra(Constants.SELECTED_USER_ITEM);
                personnelId = personnel.getId();
                mTvPersonnel.setText(personnel.getName());
                break;

        }
    }


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    @Override
    public void setUserInfo(UserInfo userInfo) {
        enterpriseId = userInfo.getPersonnel().getEnterpriseId()+"";
    }

    @Override
    public void insertDisinfectionSuccess() {

    }
}
