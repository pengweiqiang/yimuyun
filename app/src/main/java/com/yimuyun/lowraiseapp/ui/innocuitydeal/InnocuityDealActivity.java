package com.yimuyun.lowraiseapp.ui.innocuitydeal;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.base.RootActivity;
import com.yimuyun.lowraiseapp.base.contract.innocuitydeal.InnocuityDealContract;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;
import com.yimuyun.lowraiseapp.model.bean.LivestockBean;
import com.yimuyun.lowraiseapp.presenter.InnocuityDealPresenter;
import com.yimuyun.lowraiseapp.util.ToastUtil;
import com.yimuyun.lowraiseapp.widget.MsgAlertDialog;
import com.yimuyun.lowraiseapp.widget.TimeSelector;

import org.jsoup.helper.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/6/10 12:14
 * @email pengweiqiang64@163.com
 * @description 无害化处理
 * @Version
 */
public class InnocuityDealActivity extends RootActivity<InnocuityDealPresenter> implements InnocuityDealContract.View{

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

    @BindView(R.id.tv_initial_time)
    TextView mTvInitialTime;
    @BindView(R.id.tv_dead_time)
    TextView mTvDeadTime;
    @BindView(R.id.et_dead_reason)
    EditText mEtDeadReason;
    @BindView(R.id.et_innocuity_deal_way)
    EditText mEtInnocuityDealWay;
    @BindView(R.id.tv_deal_personnel)
    TextView mTvDealPersonnel;



    TimeSelector timeInitialSelector,timeDeadSelector;



    LivestockBean livestockBean;

    private String equipmentId;
    private String morbidityTime;
    String disposalTime;
    String deathTime;
    String deathReason;
    String personnelId = "1";
    String disposalMethod;
    @Override
    protected int getLayout() {
        return R.layout.activity_innocuity_deal;
    }


    @Override
    protected void initEventAndData() {
        setToolBar(mToolBar, "无害化处理", "提交", R.mipmap.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()) {
                    showConfirmDialog();
                }
            }
        });
        equipmentId = getIntent().getStringExtra(Constants.EQUPIMENT_ID);
        if(StringUtil.isBlank(equipmentId)){
            ToastUtil.show("耳标为空");
            return;
        }
        initTimeSelector();


        stateLoading();
        mPresenter.getLivestockInfo(equipmentId);
    }

    private void initTimeSelector(){
        timeInitialSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String showTime,String paramTime) {
                mTvInitialTime.setText(showTime);
                morbidityTime = paramTime;
            }
        }, "1989-01-01", "2099-12-31");

        timeDeadSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String showTime,String paramTime) {
                mTvDeadTime.setText(showTime);
                deathTime = paramTime;
            }
        }, "1989-01-01", "2099-12-31");
    }




    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    private EquipmentDetailVo equipmentDetailVo;

    private void showLiveStockInfo(){
        if(livestockBean!=null){
            String isPregnancy = livestockBean.getIsPregnancy();


            if("1".equals(isPregnancy)){//已孕
                mTvFertilization.setText("已孕");
            }else{
                mTvFertilization.setText("未孕");
            }
            if(equipmentDetailVo.getVarieties()!=null) {
                mTvLiveStockName.setText(equipmentDetailVo.getVarieties().getName());
            }
            mTvEquipmentNumber.setText("耳标编号："+livestockBean.getEquipmentId());
            String sex = livestockBean.getSex();
            StringBuffer stringBuffer = new StringBuffer();
            if(String.valueOf(Constants.SEX_FEMALE).equals(sex)){
                stringBuffer.append("母 ");
                mTvFertilization.setVisibility(View.VISIBLE);
            }else if(String.valueOf(Constants.SEX_MALE).equals(sex)){
                stringBuffer.append("公 ");
            }
//            stringBuffer.append(livestockBean.getLairageWeight());
            mTvLiveStockWeight.setText(stringBuffer.toString());

            Glide.with(mContext).load(livestockBean.getPicture()).placeholder(new ColorDrawable(mContext.getResources().getColor(R.color.color_line_grey))).//加载中显示的图片
                    error(new ColorDrawable(mContext.getResources().getColor(R.color.color_line_grey)))//加载失败时显示的图片
                    .centerCrop()
                    .into(mIvLiveStockHead);
        }
    }


    @OnClick({R.id.tv_initial_time,R.id.tv_dead_time})
    public void selectDate(View view){
        switch (view.getId()){
            case R.id.tv_initial_time:
                timeInitialSelector.show();
                break;
            case R.id.tv_dead_time:
                timeDeadSelector.show();
                break;

        }
    }

    private void showConfirmDialog(){
        final MsgAlertDialog msgAlertDialog = new MsgAlertDialog(mContext);
        msgAlertDialog.show();
        msgAlertDialog.setMsgText("提交无害化处理信息后\n该耳标即停止追溯，确定提交？");
        msgAlertDialog.setConfirmOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgAlertDialog.dismiss();
                stateLoading();
                mPresenter.insertDisposal(morbidityTime,disposalTime,deathTime,deathReason,personnelId,equipmentId,disposalMethod);
            }
        });

    }
    private boolean checkInput(){
        boolean isPass = false;
        disposalTime = morbidityTime;
        if(StringUtil.isBlank(morbidityTime)){
            showErrorMsg("请选择发布时间");
            return false;
        }
        if(StringUtil.isBlank(disposalTime)){
            showErrorMsg("请选择发布时间");
            return false;
        }
        if(StringUtil.isBlank(deathTime)){
            showErrorMsg("请选择死亡时间");
            return false;
        }
        deathReason = mEtDeadReason.getText().toString().trim();
        if(StringUtil.isBlank(deathReason)){
            showErrorMsg("请输入死亡原因");
            return false;
        }
        disposalMethod = mEtInnocuityDealWay.getText().toString().trim();
        if(StringUtil.isBlank(disposalMethod)){
            showErrorMsg("请输入无害化处理方法");
            return false;
        }

        if(StringUtil.isBlank(personnelId)){
            showErrorMsg("请选择处理人员");
            return false;
        }


        isPass = true;
        return isPass;
    }

    @Override
    public void setLiveStockInfo(EquipmentDetailVo equipmentDetailVo) {
        this.equipmentDetailVo = equipmentDetailVo;
        livestockBean = equipmentDetailVo.getLivestock();
        showLiveStockInfo();
    }

    @Override
    public void insertDisposalSuccess() {

    }
}
