package com.yimuyun.lowraiseapp.ui.offsale;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.base.LowBaseRootActivity;
import com.yimuyun.lowraiseapp.base.contract.offsale.OffSaleContract;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;
import com.yimuyun.lowraiseapp.presenter.OffSalePresenter;
import com.yimuyun.lowraiseapp.ui.feed.EquipmentDetailAdapter;
import com.yimuyun.lowraiseapp.util.DateUtil;

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
 * @description 线下销售
 * @Version
 */
public class OffSaleActivity extends LowBaseRootActivity<OffSalePresenter> implements OffSaleContract.View{

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_custom_name)
    EditText mEtCustomName;
    @BindView(R.id.et_unit_price)
    EditText mEtUnitPrice;

    @BindView(R.id.listview)
    SwipeMenuListView mListView;

    EquipmentDetailAdapter equipmentDetailAdapter;
    private List<EquipmentDetailVo> equipmentDetailVoList=new ArrayList<>();


//    StringBuffer equipmentIds=new StringBuffer();
    Map<String,String> equipmentIdMap = new HashMap<>();
    Map<String,String> equipmentRealIdMap = new HashMap<>();
    String salesTime;
    String customerName;
    String contactWay;
    String unitPrice;
    String weight;

    @Override
    protected int getLayout() {
        return R.layout.activity_off_sale;
    }


    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        setToolBar(mToolBar, "线下销售", "提交", R.mipmap.ic_left_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()){
                    stateLoading();
                    Set<String> keys = equipmentRealIdMap.keySet();
                    StringBuffer equipmentIds = new StringBuffer();
                    for (String key : keys) {
                        equipmentIds.append(key+",");
                    }
                    mPresenter.insertOffSale(equipmentIds.toString().substring(0,equipmentIds.toString().length()-1),salesTime,customerName,contactWay,unitPrice,weight);
                }
            }
        });


        equipmentDetailAdapter = new EquipmentDetailAdapter(mContext,equipmentDetailVoList);
        mListView.setAdapter(equipmentDetailAdapter);

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

    @Override
    public void setEquipmentDetail(EquipmentDetailVo equipmentDetailVo,String equipmentId) {
        findViewById(R.id.ll_equipments).setVisibility(View.VISIBLE);
        equipmentDetailVoList.add(0,equipmentDetailVo);
        equipmentDetailAdapter.setDatas(equipmentDetailVoList);

        equipmentIdMap.put(equipmentId,equipmentId);
        String equipRealId = equipmentDetailVo.getLivestock().getEquipmentId()+"";
        equipmentRealIdMap.put(equipRealId,equipRealId);

    }

    @OnClick(R.id.btn_add_ear_tag)
    public void addEarTag(View view){
    }
    @Override
    public void getTagId(String tagId) {
        String equipmentId = tagId;
        if(equipmentIdMap.containsKey(equipmentId)){
            showErrorMsg("已扫描耳标"+equipmentId);
            return;
        }
        stateLoading();
        mPresenter.getEquipmentDetailById(equipmentId);
    }


    private boolean checkInput(){
        boolean isPass = false;
        if(equipmentIdMap.isEmpty()){
            showErrorMsg("请录入耳标");
            return isPass;
        }
        customerName = mEtCustomName.getText().toString().trim();
        if(StringUtil.isBlank(customerName)){
            mEtCustomName.requestFocus();
            return false;
        }
        contactWay = mEtPhone.getText().toString().trim();
        if(StringUtil.isBlank(contactWay)){
            mEtPhone.requestFocus();
            return false;
        }
        unitPrice = mEtUnitPrice.getText().toString().trim();
        if(StringUtil.isBlank(unitPrice)){
            mEtUnitPrice.requestFocus();
            return false;
        }
        salesTime = DateUtil.format(new Date(),"yyyyMMdd");
        isPass = true;
        return isPass;
    }




    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }



    @Override
    public void insertOffSaleSuccess() {
        equipmentDetailVoList.clear();
        equipmentDetailAdapter.notifyDataSetChanged();
        equipmentIdMap.clear();
    }
}
