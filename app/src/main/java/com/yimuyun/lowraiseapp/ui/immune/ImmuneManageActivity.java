package com.yimuyun.lowraiseapp.ui.immune;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.base.LowBaseRootActivity;
import com.yimuyun.lowraiseapp.base.contract.immune.ImmuneContract;
import com.yimuyun.lowraiseapp.model.bean.DrugBean;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;
import com.yimuyun.lowraiseapp.model.bean.Personnel;
import com.yimuyun.lowraiseapp.presenter.ImmunePresenter;
import com.yimuyun.lowraiseapp.ui.UserListActivity;
import com.yimuyun.lowraiseapp.ui.diagnosis.DrugListActivity;
import com.yimuyun.lowraiseapp.ui.feed.EquipmentDetailAdapter;
import com.yimuyun.lowraiseapp.util.DateUtil;
import com.yimuyun.lowraiseapp.util.ToastUtil;
import com.yimuyun.lowraiseapp.widget.TimeSelector;

import org.jsoup.helper.StringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yimuyun.lowraiseapp.util.SystemUtil.dp2px;

/**
 * @author will on 2017/6/10 12:14
 * @email pengweiqiang64@163.com
 * @description 免疫管理
 * @Version
 */
public class ImmuneManageActivity extends LowBaseRootActivity<ImmunePresenter> implements ImmuneContract.View{

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    @BindView(R.id.tv_drug_name)
    TextView mTvDrugName;
    @BindView(R.id.tv_immune_time)
    TextView mTvImmnueTime;
    @BindView(R.id.tv_immnue_personnel)
    TextView mTvPersonnel;

    @BindView(R.id.rb_way_injection)
    RadioButton mRbWayInject;
    @BindView(R.id.rb_oral_medication)
    RadioButton mRbOralMedication;


    Map<String,String> equipmentIdMap = new HashMap<>();

    String immuneTime;
    long vaccineType;
    String immuneWay = "注射";
    long immunePersonnelId;

    TimeSelector timeSelector;

    @BindView(R.id.listview)
    SwipeMenuListView mListView;

    EquipmentDetailAdapter equipmentDetailAdapter;
    private List<EquipmentDetailVo> equipmentDetailVoList=new ArrayList<>();
    @Override
    protected int getLayout() {
        return R.layout.activity_immune;
    }


    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        setToolBar(mToolBar, "免疫管理", "提交", R.mipmap.ic_left_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()) {
                    stateLoading();
                    Set<String> keys = equipmentIdMap.keySet();
                    StringBuffer equipmentIds = new StringBuffer();
                    for (String key : keys) {
                        equipmentIds.append(key+",");
                    }
                    mPresenter.insertImmune(equipmentIds.toString().substring(0,equipmentIds.toString().length()-1),immuneTime,vaccineType+"",immuneWay,immunePersonnelId+"");
                }
            }
        });

        timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String showTime,String paramTime) {
                immuneTime = paramTime;
                mTvImmnueTime.setText(showTime);
            }
        }, DateUtil.format(new Date(),"yyyy-MM-dd"), "2099-12-31");

        mRbWayInject.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                immuneWay = mRbWayInject.getText().toString().trim();
            }
        });

        mRbOralMedication.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                immuneWay = mRbOralMedication.getText().toString().trim();
            }
        });

        equipmentDetailAdapter = new EquipmentDetailAdapter(mContext,equipmentDetailVoList);
        mListView.setAdapter(equipmentDetailAdapter);
        mListView.addFooterView(new View(this));

        initMenuListView();
    }


    private void initMenuListView(){
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.parseColor("#FD3F34")));
                // set item width
                openItem.setWidth(dp2px(60));
                // set item title
                openItem.setTitle("删除");
                // set item title fontsize
                openItem.setTitleSize(14);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);

            }
        };

        // set creator
        mListView.setMenuCreator(creator);

        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // delete
                        EquipmentDetailVo equipmentDetailVo = equipmentDetailVoList.remove(position);
                        equipmentIdMap.remove(String.valueOf(equipmentDetailVo.getLivestock().getEquipmentId()));
                        equipmentDetailAdapter.notifyDataSetChanged();
                        break;

                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }

    @OnClick(R.id.btn_add_ear_tag)
    public void addEarTag(View view){

    }
    @Override
    public void getTagId(String tagId) {
        String equipmentId = tagId;//TODO 获取耳标
        if(equipmentIdMap.containsKey(equipmentId)){
            showErrorMsg("已扫描耳标"+equipmentId);
            return;
        }
        synchronized (this) {
            stateLoading();
            mPresenter.getEquipmentDetailById(equipmentId);
        }
    }

    @OnClick(R.id.tv_immune_time)
    public void selectDate(View view){
        timeSelector.show();
    }

    @OnClick(R.id.tv_immnue_personnel)
    public void selectPersonnal(View view){
        UserListActivity.open(ImmuneManageActivity.this);
    }

    private boolean checkInput(){
        boolean isPassed = false;

        if(vaccineType == 0 ){
            showErrorMsg("请选择药品");
            return false;
        }
        if(StringUtil.isBlank(immuneTime)){
            showErrorMsg("请选择免疫日期");
            return false;
        }
        if(StringUtil.isBlank(immuneWay)){
            showErrorMsg("请选择免疫方法");
            return false;
        }
        if(immunePersonnelId == 0){
            showErrorMsg("请选择免疫人员");
            return false;
        }
        if(equipmentIdMap.isEmpty()){
            showErrorMsg("请录入耳标");
            return isPassed;
        }

        isPassed = true;
        return isPassed;
    }


    @OnClick(R.id.tv_drug_name)
    public void selectDrug(View view){
        DrugListActivity.open(ImmuneManageActivity.this,vaccineType,"1");
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null){
            return;
        }
        switch (requestCode){

            case Constants.REQUEST_DRUG://药品
                DrugBean drugBean = (DrugBean) data.getSerializableExtra(Constants.SELECTED_DRUG);
                vaccineType = drugBean.getId();
                mTvDrugName.setText(drugBean.getDrugName());
                break;
            case Constants.REQUEST_USER_ITEM://选择人员
                Personnel personnel = (Personnel) data.getSerializableExtra(Constants.SELECTED_USER_ITEM);
                immunePersonnelId = personnel.getId();
                mTvPersonnel.setText(personnel.getName());
                break;

        }
    }


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    @Override
    public void setEquipmentDetail(EquipmentDetailVo equipmentDetailVo, String equipmentId) {
        findViewById(R.id.ll_equipments).setVisibility(View.VISIBLE);
        equipmentDetailVoList.add(0,equipmentDetailVo);
        equipmentDetailAdapter.setDatas(equipmentDetailVoList);

        equipmentIdMap.put(equipmentId,equipmentId);
    }

    @Override
    public void insertImmuneSuccess() {
        equipmentIdMap.clear();
        equipmentDetailVoList.clear();
        equipmentDetailAdapter.notifyDataSetChanged();

        immuneTime = "";
        vaccineType =0;
        immuneWay = "注射";
        immunePersonnelId=0;
        mTvDrugName.setText("");
        mTvImmnueTime.setText("");
        mTvPersonnel.setText("");
        mRbWayInject.setChecked(true);
    }
}
