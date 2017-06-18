package com.yimuyun.lowraiseapp.ui.purchase;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.base.RootActivity;
import com.yimuyun.lowraiseapp.base.contract.purchase.PurchaseContract;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;
import com.yimuyun.lowraiseapp.presenter.PurchasePresenter;
import com.yimuyun.lowraiseapp.ui.feed.EquipmentDetailAdapter;

import org.jsoup.helper.StringUtil;

import java.util.ArrayList;
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
 * @description 收购入栏
 * @Version
 */
public class PurchaseActivity extends RootActivity<PurchasePresenter> implements PurchaseContract.View{

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    @BindView(R.id.listview)
    SwipeMenuListView mListView;

    EquipmentDetailAdapter equipmentDetailAdapter;
    private List<EquipmentDetailVo> equipmentDetailVoList=new ArrayList<>();


//    StringBuffer equipmentIds=new StringBuffer();
    Map<String,String> equipmentIdMap = new HashMap<>();
    Map<String,String> equipIdRealMap = new HashMap<>();

    String enterpriseId;
    String personnelId;

    String equipmentIdCurrent;

    @Override
    protected int getLayout() {
        return R.layout.activity_purchase;
    }


    @Override
    protected void initEventAndData() {
        setToolBar(mToolBar, "收购入栏");


        equipmentDetailAdapter = new EquipmentDetailAdapter(mContext,equipmentDetailVoList);
        mListView.setAdapter(equipmentDetailAdapter);

        initMenuListView();


        equipmentIdCurrent = getIntent().getStringExtra(Constants.EQUPIMENT_ID);
        getEquipmentDetail();

        getUserInfo();
    }

    private void getUserInfo(){
        String phoneNumber = App.getInstance().getUserBeanInstance().getPhoneNumber();
        mPresenter.getUserInfo(phoneNumber);
    }

    private void getEquipmentDetail(){
        stateLoading();
        mPresenter.getEquipmentDetailById(equipmentIdCurrent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        equipmentIdCurrent = intent.getStringExtra(Constants.EQUPIMENT_ID);
        getEquipmentDetail();
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
    public void setUserInfo(UserInfo userInfo) {
        if(userInfo.getPersonnel()!=null){
            enterpriseId = String.valueOf(userInfo.getPersonnel().getEnterpriseId());
            personnelId = String.valueOf(userInfo.getPersonnel().getId());
        }
    }

    @Override
    public void setEquipmentDetail(EquipmentDetailVo equipmentDetailVo,String equipmentId) {
        equipmentDetailVoList.add(0,equipmentDetailVo);
        equipmentDetailAdapter.setDatas(equipmentDetailVoList);

        equipmentIdMap.put(equipmentId,equipmentId);
        String equipIdReal = equipmentDetailVo.getLivestock().getEquipmentId()+"";
        equipIdRealMap.put(equipIdReal,equipIdReal);

    }

    @OnClick(R.id.btn_add)
    public void btnAddPurchase(View view){
        if(checkInput()){
            stateLoading();
            Set<String> keys = equipIdRealMap.keySet();
            StringBuffer equipmentIds = new StringBuffer();
            for (String key : keys) {
                equipmentIds.append(key+",");
            }
            mPresenter.insertSaleBuy(equipmentIds.toString().substring(0,equipmentIds.toString().length()-1),enterpriseId,personnelId);
        }
    }
    @Override
    public void insertSaleBuySuccess() {
        equipmentDetailVoList.clear();
        equipmentDetailAdapter.notifyDataSetChanged();
        equipmentIdMap.clear();
        PurchaseSuccessActivity.open(mContext);
    }



    private boolean checkInput(){
        boolean isPass = false;
        if(equipmentIdMap.isEmpty()){
            showErrorMsg("请录入耳标");
            return isPass;
        }
        if(StringUtil.isBlank(enterpriseId)){
            showErrorMsg("企业id获取为空");
            return false;
        }
        if(StringUtil.isBlank(personnelId)){
            showErrorMsg("personnelId获取为空");
            return false;
        }
        isPass = true;
        return isPass;
    }




    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }



}
