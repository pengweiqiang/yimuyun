package com.yimuyun.lowraiseapp.ui.diagnosis;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.base.RootActivity;
import com.yimuyun.lowraiseapp.base.contract.diagnosis.AddDiagnosisContract;
import com.yimuyun.lowraiseapp.model.bean.DiagnosisResultBean;
import com.yimuyun.lowraiseapp.model.bean.DiagnosisTreatmentBean;
import com.yimuyun.lowraiseapp.model.bean.DiagnosisTreatmentPlanBean;
import com.yimuyun.lowraiseapp.model.bean.DiagnosisTreatmentVo;
import com.yimuyun.lowraiseapp.model.bean.DrugBean;
import com.yimuyun.lowraiseapp.model.bean.Personnel;
import com.yimuyun.lowraiseapp.presenter.AddDiagnosisPresenter;
import com.yimuyun.lowraiseapp.ui.UserListActivity;
import com.yimuyun.lowraiseapp.util.DateUtil;
import com.yimuyun.lowraiseapp.widget.TimeSelector;

import org.jsoup.helper.StringUtil;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/6/10 12:14
 * @email pengweiqiang64@163.com
 * @description 添加诊疗记录
 * @Version
 */
public class AddDiagnosisRecordActivity extends RootActivity<AddDiagnosisPresenter> implements AddDiagnosisContract.View{

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    @BindView(R.id.tv_diagnosis_result)
    TextView mTvDiagnosisResult;
    @BindView(R.id.tv_diagnosis_way)
    TextView mTvDiagnosisWay;
    @BindView(R.id.tv_drug_name)
    TextView mTvDiagnosisDrugName;
    @BindView(R.id.tv_diagnosis_personnel)
    TextView mTvDiagnosisPersonnal;
    @BindView(R.id.tv_feed_time)
    TextView mTvFeedTime;


    long treatmentPlanId;//诊疗方案id
    long personnelId;
    String symptoms;//症状
    long result;//诊疗结果
    long dragId;//药品
    String time;

    TimeSelector timeSelector;

    private DiagnosisTreatmentVo diagnosisTreatmentVo;

    @Override
    protected int getLayout() {
        return R.layout.activity_add_diagnosis;
    }


    @Override
    protected void initEventAndData() {
        setToolBar(mToolBar, "添加记录", "提交", R.mipmap.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()) {
                    setDiagnosisTreatment();
                }
            }
        });

        timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String showTime,String paramTime) {
                time = paramTime;
                mTvFeedTime.setText(showTime);
            }
        }, DateUtil.format(new Date(),"yyyy-MM-dd"), "2099-12-31");

        diagnosisTreatmentVo = (DiagnosisTreatmentVo)getIntent().getSerializableExtra(Constants.ADD_DIAGNOSI_RECORD);

        initDataView();
    }

    private void initDataView(){
        if(diagnosisTreatmentVo!=null){
            dragId = diagnosisTreatmentVo.getDrug().getId();
            mTvDiagnosisDrugName.setText(diagnosisTreatmentVo.getDrug().getDrugName());


            treatmentPlanId = diagnosisTreatmentVo.getDiagnosisTreatmentPlan().getId();
            mTvDiagnosisWay.setText(diagnosisTreatmentVo.getDiagnosisTreatmentPlan().getTreatmentPlan());

            personnelId = diagnosisTreatmentVo.getDiagnosisTreatment().getPersonnelId();
            mTvDiagnosisPersonnal.setText(diagnosisTreatmentVo.getDiagnosisTreatment().getDocumentNumber());
//            mTvDiagnosisPersonnal.setText(diagnosisTreatmentVo.getDiagnosisTreatment().get);

            result = diagnosisTreatmentVo.getDiagnosisResult().getId();
            symptoms = diagnosisTreatmentVo.getDiagnosisResult().getDiagnosisResult();
            mTvDiagnosisResult.setText(symptoms);

            time = DateUtil.formartTime2String_(diagnosisTreatmentVo.getDiagnosisTreatment().getTime());
            mTvFeedTime.setText(DateUtil.formartTime2String(diagnosisTreatmentVo.getDiagnosisTreatment().getTime()));
        }
    }

    public static void open(Activity activity,DiagnosisTreatmentVo diagnosisTreatmentVo){
        Intent intent = new Intent(activity,AddDiagnosisRecordActivity.class);
        intent.putExtra(Constants.ADD_DIAGNOSI_RECORD,diagnosisTreatmentVo);
        activity.startActivityForResult(intent, Constants.REQUEST_ADD_DIAGNOSIS_RECORD);
    }

    @OnClick(R.id.tv_feed_time)
    public void onClick(View view){
        timeSelector.show();
    }
    @OnClick(R.id.tv_diagnosis_result)
    public void selectDiagnosisResult(View view){
        DiagnosisResultListActivity.open(AddDiagnosisRecordActivity.this,result);
    }

    @OnClick(R.id.tv_diagnosis_personnel)
    public void selectPersonnal(View view){
        UserListActivity.open(AddDiagnosisRecordActivity.this);
    }

    @OnClick(R.id.tv_diagnosis_way)
    public void selectTreatmentPlan(View view){
        DiagnosisTreatmentPlanListActivity.open(AddDiagnosisRecordActivity.this,treatmentPlanId);
    }

    @OnClick(R.id.tv_drug_name)
    public void selectDrug(View view){
        DrugListActivity.open(AddDiagnosisRecordActivity.this,dragId,"3");
    }

    private boolean checkInput(){
        boolean isPassed = false;
        if(treatmentPlanId == 0){
            showErrorMsg("请选择诊疗方案");
            return false;
        }
        if(StringUtil.isBlank(symptoms)){
            showErrorMsg("symptoms症状获取为空");
            return false;
        }
        if(result == 0){
            showErrorMsg("请选择诊疗结果");
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

        isPassed = true;
        return isPassed;
    }



    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    public void setDiagnosisTreatment(){
        DiagnosisTreatmentVo diagnosisTreatmentVo = new DiagnosisTreatmentVo();

        DiagnosisTreatmentBean diagnosisTreatmentBean = new DiagnosisTreatmentBean();
        diagnosisTreatmentBean.setDragId(dragId);
        diagnosisTreatmentBean.setDocumentNumber(mTvDiagnosisPersonnal.getText().toString());
        diagnosisTreatmentBean.setPersonnelId(personnelId);
        diagnosisTreatmentBean.setResult(symptoms);
        diagnosisTreatmentBean.setSymptoms(symptoms);
        Date dateTime = DateUtil.parse(time,"yyyyMMdd");
        diagnosisTreatmentBean.setTime(dateTime.getTime());
        diagnosisTreatmentVo.setDiagnosisTreatment(diagnosisTreatmentBean);

        DiagnosisResultBean diagnosisResultBean = new DiagnosisResultBean();
        diagnosisResultBean.setId(result);
        diagnosisResultBean.setDiagnosisResult(mTvDiagnosisResult.getText().toString());
        diagnosisTreatmentVo.setDiagnosisResult(diagnosisResultBean);

        DiagnosisTreatmentPlanBean diagnosisTreatmentPlan = new DiagnosisTreatmentPlanBean();
        diagnosisTreatmentPlan.setTreatmentPlan(mTvDiagnosisWay.getText().toString());
        diagnosisTreatmentPlan.setId(treatmentPlanId);
        diagnosisTreatmentVo.setDiagnosisTreatmentPlan(diagnosisTreatmentPlan);

        DrugBean drugBean = new DrugBean();
        drugBean.setId(dragId);
        drugBean.setDrugName(mTvDiagnosisDrugName.getText().toString());
        diagnosisTreatmentVo.setDrug(drugBean);

        Intent intent = new Intent();
        intent.putExtra(Constants.ADD_DIAGNOSI_RECORD,diagnosisTreatmentVo);
        setResult(RESULT_OK,intent);
        finish();

    }
    @Override
    public void insertDiagnosisSucecess(DiagnosisTreatmentVo diagnosisTreatmentVo) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null){
            return;
        }
        switch (requestCode){
            case Constants.REQUEST_ADD_DIAGNOSIS_RECORD://诊疗结果
                DiagnosisResultBean diagnosisResultBean = (DiagnosisResultBean) data.getSerializableExtra(Constants.SELECTED_DIAGNOSIS_RESULT);
                result = diagnosisResultBean.getId();
                symptoms = diagnosisResultBean.getDiagnosisResult();
                mTvDiagnosisResult.setText(diagnosisResultBean.getDiagnosisResult());
                break;
            case Constants.REQUEST_ADD_DIAGNOSIS_TREATMENT_PLAN://诊疗方案
                DiagnosisTreatmentPlanBean diagnosisTreatmentPlanBean = (DiagnosisTreatmentPlanBean) data.getSerializableExtra(Constants.SELECTED_DIAGNOSIS_TREATMENT_PLAN);
                treatmentPlanId = diagnosisTreatmentPlanBean.getId();
                mTvDiagnosisWay.setText(diagnosisTreatmentPlanBean.getTreatmentPlan());
                break;
            case Constants.REQUEST_DRUG://药品
                DrugBean drugBean = (DrugBean) data.getSerializableExtra(Constants.SELECTED_DRUG);
                dragId = drugBean.getId();
                mTvDiagnosisDrugName.setText(drugBean.getDrugName());
                break;
            case Constants.REQUEST_USER_ITEM://选择人员
                Personnel personnel = (Personnel) data.getSerializableExtra(Constants.SELECTED_USER_ITEM);
                personnelId = personnel.getId();
                mTvDiagnosisPersonnal.setText(personnel.getName());
                break;

        }
    }
}
