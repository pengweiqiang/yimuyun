package com.yimuyun.lowraiseapp.ui.diagnosis;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.base.RootActivity;
import com.yimuyun.lowraiseapp.base.contract.diagnosis.DiagnosisContract;
import com.yimuyun.lowraiseapp.model.bean.DiagnosisTreatmentVo;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;
import com.yimuyun.lowraiseapp.model.bean.LivestockBean;
import com.yimuyun.lowraiseapp.presenter.DiagnosisPresenter;
import com.yimuyun.lowraiseapp.util.DateUtil;
import com.yimuyun.lowraiseapp.util.ToastUtil;
import com.yimuyun.lowraiseapp.widget.MsgAlertDialog;

import org.jsoup.helper.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/6/10 12:14
 * @email pengweiqiang64@163.com
 * @description 诊疗管理
 * @Version
 */
public class DiagnosisManageActivity extends RootActivity<DiagnosisPresenter> implements DiagnosisContract.View{

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    @BindView(R.id.iv_livestock_head)
    ImageView mIvLiveStockHead;
    @BindView(R.id.tv_livestock_name)
    TextView mTvLiveStockName;
    @BindView(R.id.tv_livestock_weight)
    TextView mTvLiveStockWeight;
    @BindView(R.id.tv_equipment_number)
    TextView mTvEquipmentNumber;
    @BindView(R.id.tv_fertilization)
    TextView mTvFertilization;

    @BindView(R.id.listview)
    ListView mListView;

    DiagnosisAdapter diagnosisAdapter;
    private LivestockBean livestockBean;
    private List<DiagnosisTreatmentVo> diagnosisTreatmentVoList = new ArrayList<>();


    private String equipmentId;//耳标 应该从低频获取

    DiagnosisTreatmentVo diagnosisTreatmentVo;
    @Override
    protected int getLayout() {
        return R.layout.activity_diagnosis;
    }


    @Override
    protected void initEventAndData() {
        setToolBar(mToolBar, "诊疗管理", "提交", R.mipmap.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(diagnosisTreatmentVo==null){
                    showErrorMsg("请先添加记录");
                    return;
                }
                stateLoading();
                String personnelId = String.valueOf(diagnosisTreatmentVo.getDiagnosisTreatment().getPersonnelId());
                String treatmentPlanId = String.valueOf(diagnosisTreatmentVo.getDiagnosisTreatmentPlan().getId());
                String symptoms = diagnosisTreatmentVo.getDiagnosisTreatment().getSymptoms();
                String result = diagnosisTreatmentVo.getDiagnosisResult().getDiagnosisResult();
                String dragId = diagnosisTreatmentVo.getDrug().getId()+"";
                String time = DateUtil.formartTime2String_(diagnosisTreatmentVo.getDiagnosisTreatment().getTime());
                mPresenter.insertDiagnosisTreatment(equipmentId,personnelId,treatmentPlanId,symptoms,result,dragId,time);
            }
        });
        equipmentId = getIntent().getStringExtra(Constants.EQUPIMENT_ID);
        if(StringUtil.isBlank(equipmentId)){
            ToastUtil.show("耳标为空");
            return;
        }
        diagnosisAdapter = new DiagnosisAdapter(mContext,diagnosisTreatmentVoList);
        mListView.setAdapter(diagnosisAdapter);

        diagnosisAdapter.setOnViewOnclickListener(new DiagnosisAdapter.OnViewOnclickListener() {
            @Override
            public void onItemviewclick(View view, int position) {
                switch (view.getId()){
                    case R.id.tv_edit:
//                        showEditItemDialog(position);
                        showEditDialog();
                        break;
                    case R.id.tv_delete:
                        showDeleteItemDialog(position);
                        break;
                }
            }
        });

        stateLoading();
        mPresenter.getLiveStockInfo(equipmentId);

        mPresenter.getDiagnosisTreatment(equipmentId);
    }




    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }



    private void showLiveStockInfo(){
        if(livestockBean!=null){
            String isPregnancy = livestockBean.getIsPregnancy();
            mTvFertilization.setVisibility(View.VISIBLE);
            if("1".equals(isPregnancy)){
                mTvFertilization.setText("已孕");
            }else{
                mTvFertilization.setText("未孕");
            }
            mTvLiveStockName.setText(equipmentDetailVo.getVarieties().getName());
            mTvEquipmentNumber.setText("耳标编号："+livestockBean.getEquipmentId());
            String sex = livestockBean.getSex();
            StringBuffer stringBuffer = new StringBuffer();
            if(String.valueOf(Constants.SEX_FEMALE).equals(sex)){
                stringBuffer.append("母 ");
            }else if(String.valueOf(Constants.SEX_MALE).equals(sex)){
                stringBuffer.append("公 ");
            }
            stringBuffer.append(livestockBean.getLairageWeight());
            mTvLiveStockWeight.setText(stringBuffer.toString());

            Glide.with(mContext).load(livestockBean.getPicture()).placeholder(new ColorDrawable(mContext.getResources().getColor(R.color.color_line_grey))).//加载中显示的图片
                    error(new ColorDrawable(mContext.getResources().getColor(R.color.color_line_grey)))//加载失败时显示的图片
                    .centerCrop()
                    .into(mIvLiveStockHead);
        }
    }


    //添加记录
    @OnClick(R.id.btn_add)
    public void addRecords(View view){
        if(diagnosisTreatmentVo!=null){
            showEditDialog();
            return;
        }
        AddDiagnosisRecordActivity.open(mContext,diagnosisTreatmentVo);
    }

    private void showEditDialog(){
        final MsgAlertDialog msgAlertDialog = new MsgAlertDialog(mContext);
        msgAlertDialog.show();
        msgAlertDialog.setMsgText("您已编辑诊疗记录\n返回上级页面将放弃提交记录更新");
        msgAlertDialog.setCancelText("返回上级", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgAlertDialog.dismiss();

            }
        });
        msgAlertDialog.setConfirmOnclickListener("继续编辑", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgAlertDialog.dismiss();
                AddDiagnosisRecordActivity.open(DiagnosisManageActivity.this,diagnosisTreatmentVo);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null){
            return;
        }
        if(diagnosisTreatmentVo==null){//新增
            diagnosisTreatmentVo = (DiagnosisTreatmentVo)data.getSerializableExtra(Constants.ADD_DIAGNOSI_RECORD);
            diagnosisTreatmentVoList.add(diagnosisTreatmentVo);
        }else{//修改
            diagnosisTreatmentVo = (DiagnosisTreatmentVo)data.getSerializableExtra(Constants.ADD_DIAGNOSI_RECORD);
            diagnosisTreatmentVoList.set(0,diagnosisTreatmentVo);
        }
        diagnosisAdapter.notifyDataSetChanged();
        mListView.setVisibility(View.VISIBLE);
    }


    //删除
    private void showDeleteItemDialog(final int position){
        final MsgAlertDialog msgAlertDialog = new MsgAlertDialog(mContext);
        msgAlertDialog.show();
        msgAlertDialog.setMsgText("确定删除该记录？");
        msgAlertDialog.setConfirmOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diagnosisTreatmentVo = null;
                diagnosisTreatmentVoList.remove(position);
                diagnosisAdapter.notifyDataSetChanged();
                msgAlertDialog.dismiss();
            }
        });

    }

    private EquipmentDetailVo equipmentDetailVo;
    @Override
    public void setLivestockInfo(EquipmentDetailVo equipmentDetailVo) {
        this.equipmentDetailVo= equipmentDetailVo;
        this.livestockBean = equipmentDetailVo.getLivestock();
        showLiveStockInfo();
    }

    @Override
    public void setDiagnosisTreatment(List<DiagnosisTreatmentVo> diagnosisTreatmentVoList) {
        this.diagnosisTreatmentVoList = diagnosisTreatmentVoList;
        if(diagnosisTreatmentVoList!=null && !diagnosisTreatmentVoList.isEmpty()) {
            mListView.setVisibility(View.VISIBLE);
        }else{
            mListView.setVisibility(View.GONE);
        }
        diagnosisAdapter.setDatas(diagnosisTreatmentVoList);

    }

    @Override
    public void insertDiagnosisTreatmentSuccess() {
        diagnosisTreatmentVo = null;
        DiagnosisTreatmentVo diagnosisTreatmentVo1 = diagnosisTreatmentVoList.get(0);
        diagnosisTreatmentVo1.getDiagnosisTreatment().setId(DateUtil.getCurrentDay());
        diagnosisAdapter.notifyDataSetChanged();
    }
}
