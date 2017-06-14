package com.yimuyun.lowraiseapp.ui.fertilization;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.base.RootActivity;
import com.yimuyun.lowraiseapp.base.contract.fertilization.FertilizationContract;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;
import com.yimuyun.lowraiseapp.model.bean.LivestockBean;
import com.yimuyun.lowraiseapp.presenter.FertilizationPresenter;
import com.yimuyun.lowraiseapp.util.ToastUtil;
import com.yimuyun.lowraiseapp.widget.GlideRoundTransform;
import com.yimuyun.lowraiseapp.widget.MsgAlertDialog;
import com.yimuyun.lowraiseapp.widget.TimeSelector;

import org.jsoup.helper.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/6/10 12:14
 * @email pengweiqiang64@163.com
 * @description 受孕管理
 * @Version
 */
public class FertilizationManageActivity extends RootActivity<FertilizationPresenter> implements FertilizationContract.View{

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

    @BindView(R.id.tv_initial_time)
    TextView mTvInitialTime;
    @BindView(R.id.tv_lairage_time)
    TextView mTvLairageTime;

    TimeSelector timeInitialSelector,timeLairageSelector;
    private String initialTime,lairageTime;

    @BindView(R.id.rb_pregnancy)
    RadioButton mRbPregancy;
    @BindView(R.id.rb_unpregnancy)
    RadioButton mRbUnPregancy;

    LivestockBean livestockBean;

    private String equipmentId;
    @Override
    protected int getLayout() {
        return R.layout.activity_fertilization_manage;
    }


    @Override
    protected void initEventAndData() {
        setToolBar(mToolBar, "受孕管理", "提交", R.mipmap.ic_left_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmDialog();
            }
        });
        equipmentId = getIntent().getStringExtra(Constants.EQUPIMENT_ID);
        if(StringUtil.isBlank(equipmentId)){
            ToastUtil.show("耳标为空");
            return;
        }
        initTimeSelector();


        stateLoading();
        mPresenter.getLiveStockInfo(equipmentId);
    }

    private void initTimeSelector(){
        timeInitialSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String showTime,String paramTime) {
                mTvInitialTime.setText(showTime);
                initialTime = paramTime;
            }
        }, "1989-01-01", "2099-12-31");

        timeLairageSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String showTime,String paramTime) {
                mTvLairageTime.setText(showTime);
                lairageTime = showTime;
            }
        }, "1989-01-01", "2099-12-31");
    }




    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    private EquipmentDetailVo equipmentDetailVo;
    @Override
    public void setLivestockInfo(EquipmentDetailVo equipmentDetailVo) {
        this.equipmentDetailVo = equipmentDetailVo;
        livestockBean = equipmentDetailVo.getLivestock();
        showLiveStockInfo();
    }

    private void showLiveStockInfo(){
        if(livestockBean!=null){
            String isPregnancy = livestockBean.getIsPregnancy();

            if("1".equals(isPregnancy)){//已孕
                mRbPregancy.setChecked(true);
            }else{
                mRbUnPregancy.setChecked(true);
            }
            if(equipmentDetailVo.getVarieties()!=null) {
                mTvLiveStockName.setText(equipmentDetailVo.getVarieties().getName());
            }
            mTvEquipmentNumber.setText("耳标编号："+livestockBean.getEquipmentId());
            String sex = livestockBean.getSex();
            StringBuffer stringBuffer = new StringBuffer();
            if(String.valueOf(Constants.SEX_FEMALE).equals(sex)){
                stringBuffer.append("母 ");
            }else if(String.valueOf(Constants.SEX_MALE).equals(sex)){
                stringBuffer.append("公 ");
                setToolBar(mToolBar,"受孕管理");
                findViewById(R.id.ll_pregnancy).setVisibility(View.GONE);
            }
//            stringBuffer.append(livestockBean.getLairageWeight());
            mTvLiveStockWeight.setText(stringBuffer.toString());

            Glide.with(mContext).load(livestockBean.getPicture()).placeholder(R.mipmap.ic_default_head).//加载中显示的图片
                    error(R.mipmap.ic_default_head)//加载失败时显示的图片
//                    .centerCrop()
                    .transform(new GlideRoundTransform(mContext,5))
                    .into(mIvLiveStockHead);
        }
    }

    @Override
    public void updatePreganacySuccess() {

    }

    @OnClick({R.id.tv_initial_time,R.id.tv_lairage_time})
    public void selectDate(View view){
        switch (view.getId()){
            case R.id.tv_initial_time:
                timeInitialSelector.show();
                break;
            case R.id.tv_lairage_time:
                timeLairageSelector.show();
                break;

        }
    }

    private void showConfirmDialog(){
        final MsgAlertDialog msgAlertDialog = new MsgAlertDialog(mContext);
        msgAlertDialog.show();
        msgAlertDialog.setMsgText("确定提交受孕信息？");
        msgAlertDialog.setConfirmOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgAlertDialog.dismiss();
                stateLoading();
                boolean isPregancy = mRbPregancy.isChecked();
                String type = "2";
                if(isPregancy){
                    type = "1";
                }
                mPresenter.updatePregnancy(equipmentId,type);
            }
        });

    }

}
