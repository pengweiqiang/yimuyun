package com.yimuyun.lowraiseapp.ui.weight;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.base.RootActivity;
import com.yimuyun.lowraiseapp.base.contract.weight.WeightContract;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;
import com.yimuyun.lowraiseapp.model.bean.LivestockBean;
import com.yimuyun.lowraiseapp.model.bean.WeightBean;
import com.yimuyun.lowraiseapp.model.bean.WeightVo;
import com.yimuyun.lowraiseapp.presenter.WeightPresenter;
import com.yimuyun.lowraiseapp.util.DateUtil;
import com.yimuyun.lowraiseapp.util.ToastUtil;
import com.yimuyun.lowraiseapp.widget.GlideRoundTransform;
import com.yimuyun.lowraiseapp.widget.MsgAlertDialog;

import org.jsoup.helper.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/6/10 12:14
 * @email pengweiqiang64@163.com
 * @description 称重管理
 * @Version
 */
public class WeightManageActivity extends RootActivity<WeightPresenter> implements WeightContract.View{


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

    WeightListAdapter weightListAdapter;
    private LivestockBean livestockBean;
    private List<WeightBean> weightBeanList;


    private String equipmentId;//耳标 应该从低频获取

    String weight = "";
    @Override
    protected int getLayout() {
        return R.layout.activity_weight_manage;
    }


    @Override
    protected void initEventAndData() {
        setToolBar(mToolBar, "称重管理", "提交", R.mipmap.ic_left_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(livestockBean==null){
                    showErrorMsg("没有选择牲畜");
                    return;
                }
                if(StringUtil.isBlank(weight)){
                    showErrorMsg("请添加称重记录");
                    return;
                }

                String weighTime = DateUtil.getCurrentDate();
                String weighPhase = "常规称重";//称重阶段
                String cultureProcess = "养殖";//养殖过程
                long weighId = App.getInstance().getUserBeanInstance().getUid();
                stateLoading();
                mPresenter.insertWeight(String.valueOf(livestockBean.getEquipmentId()),weighTime,weighPhase,cultureProcess,weight,weighId);
            }
        });
        equipmentId = getIntent().getStringExtra(Constants.EQUPIMENT_ID);
        if(StringUtil.isBlank(equipmentId)){
            ToastUtil.show("耳标为空");
            return;
        }
        mTvEquipmentNumber.setText("耳标编号："+equipmentId);
        weightListAdapter = new WeightListAdapter(mContext,weightBeanList);
        mListView.setAdapter(weightListAdapter);

        weightListAdapter.setOnViewOnclickListener(new WeightListAdapter.OnViewOnclickListener() {
            @Override
            public void onItemviewclick(View view, int position) {
                switch (view.getId()){
                    case R.id.tv_edit_weight:
                        showEditItemDialog(position);
                        break;
                    case R.id.tv_delete_weight:
                        showDeleteItemDialog(position);
                        break;
                }
            }
        });

        mListView.setVisibility(View.GONE);
        stateLoading();
        mPresenter.getWeightList(equipmentId);
    }




    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    @Override
    public void setWeightList(WeightVo weightVo) {
        this.weightBeanList = weightVo.getWeighs();
        if(weightBeanList!=null && !weightBeanList.isEmpty()){
            mListView.setVisibility(View.VISIBLE);
        }
        this.livestockBean = weightVo.getLivestock();
        weightListAdapter.setDatas(weightBeanList);
        showLiveStockInfo();
    }

    private void showLiveStockInfo(){
        if(livestockBean!=null){
            String isPregnancy = livestockBean.getIsPregnancy();
            mTvFertilization.setVisibility(View.VISIBLE);


            String sex = livestockBean.getSex();
            StringBuffer stringBuffer = new StringBuffer();
            if(String.valueOf(Constants.SEX_FEMALE).equals(sex)){
                stringBuffer.append("母 ");
                if("1".equals(isPregnancy)){
                    mTvFertilization.setVisibility(View.VISIBLE);
                    mTvFertilization.setText("已孕");
                }else{
                    mTvFertilization.setText("未孕");
                }
            }else if(String.valueOf(Constants.SEX_MALE).equals(sex)){
                stringBuffer.append("公 ");
                mTvFertilization.setVisibility(View.GONE);
            }
            stringBuffer.append(livestockBean.getLairageWeight()+"公斤");
            mTvLiveStockWeight.setText(stringBuffer.toString());

            Glide.with(mContext).load(livestockBean.getPicture()).placeholder(R.mipmap.ic_default_head).//加载中显示的图片
                    error(R.mipmap.ic_default_head)//加载失败时显示的图片
                    .transform(new GlideRoundTransform(mContext,5))
//                    .centerCrop()
                    .into(mIvLiveStockHead);
        }
    }

    @Override
    public void insertWeightSuccess() {
        weight = "";
        WeightBean weightBean = weightBeanList.get(0);
        weightBean.setId(DateUtil.getCurrentDay());
        weightListAdapter.notifyDataSetChanged();
        mListView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setLivestockInfo(EquipmentDetailVo equipmentDetailVo) {
        if(equipmentDetailVo.getVarieties()!=null){
            mTvLiveStockName.setText(equipmentDetailVo.getVarieties().getName());
        }
//        if(equipmentDetailVo.getParentEquipment()!=null){
//            mTvEquipmentNumber.setText("耳标编号："+equipmentDetailVo.getParentEquipment().getEquipmentNumber());
//        }
    }

    //添加记录
    @OnClick(R.id.btn_add)
    public void addWeightRecords(View view){
        if(!StringUtil.isBlank(weight)){
            final MsgAlertDialog msgAlertDialog = new MsgAlertDialog(mContext);
            msgAlertDialog.show();
            msgAlertDialog.setMsgText("您已编辑称重记录\n返回上级页面将放弃提交记录更新");
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
                    showEditItemDialog(0);
                }
            });

            return;
        }
        showInputDialog("");
    }

    private void showInputDialog(String inputText){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View editView = LayoutInflater.from(this).inflate(R.layout.dialog_input_layout, null);
        final EditText editNum = (EditText) editView.findViewById(R.id.et_input);
        editNum.setText(inputText);
        final AlertDialog alertDialog = builder.setView(editView).create();
        alertDialog.show();
        alertDialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isNewAdd = false;
                if(StringUtil.isBlank(weight)){
                    isNewAdd = true;
                }
                weight = editNum.getText().toString().trim();
                if(StringUtil.isBlank(weight)){
                    return;
                }
                alertDialog.dismiss();
                if(isNewAdd) {//新增
                    WeightBean weightBean = new WeightBean();
                    weightBean.setLivestockId(livestockBean.getId());
                    weightBean.setWeighTime(System.currentTimeMillis());
                    weightBean.setWeight(weight);
                    weightBean.setWeighPhase("常规称重");

                    weightBeanList.add(0, weightBean);
                }else{//编辑
                    WeightBean weightBean = weightBeanList.get(0);
                    weightBean.setWeight(weight);
                }
                if(weightBeanList!=null && !weightBeanList.isEmpty()){
                    mListView.setVisibility(View.VISIBLE);
                }
                weightListAdapter.setDatas(weightBeanList);
            }
        });
    }

    //删除
    private void showDeleteItemDialog(final int position){
        final MsgAlertDialog msgAlertDialog = new MsgAlertDialog(mContext);
        msgAlertDialog.show();
        msgAlertDialog.setMsgText("确定删除该记录？");
        msgAlertDialog.setConfirmOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight="";
                weightBeanList.remove(position);
                weightListAdapter.notifyDataSetChanged();
                msgAlertDialog.dismiss();
            }
        });

    }
    private void showEditItemDialog(final int position){
        String inputText = "";
        if(weightBeanList!=null){
            inputText = weightBeanList.get(position).getWeight();
        }
        showInputDialog(inputText);
    }
}
