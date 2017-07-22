package com.yimuyun.lowraiseapp.ui.feed;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.base.LowBaseRootActivity;
import com.yimuyun.lowraiseapp.base.contract.feed.FeedContract;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;
import com.yimuyun.lowraiseapp.model.bean.FeedBean;
import com.yimuyun.lowraiseapp.presenter.FeedPresenter;
import com.yimuyun.lowraiseapp.util.DateUtil;
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
 * @descripion 饲养管理
 * @Version
 */
public class FeedManageActivity extends LowBaseRootActivity<FeedPresenter> implements FeedContract.View{

    @BindView(R.id.tv_feed_name)
    TextView mTvFeedName;
    @BindView(R.id.tv_feed_time)
    TextView mTvFeedTime;

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    //草料 for fast
    @BindView(R.id.checkbox1)
    CheckBox checkBox1;
    @BindView(R.id.checkbox2)
    CheckBox checkBox2;
    @BindView(R.id.checkbox3)
    CheckBox checkBox3;
    @BindView(R.id.checkbox4)
    CheckBox checkBox4;
    @BindView(R.id.checkbox5)
    CheckBox checkBox5;
    @BindView(R.id.checkbox6)
    CheckBox checkBox6;
    @BindView(R.id.checkbox7)
    CheckBox checkBox7;
    @BindView(R.id.checkbox8)
    CheckBox checkBox8;
    @BindView(R.id.checkbox9)
    CheckBox checkBox9;
    @BindView(R.id.checkbox10)
    CheckBox checkBox10;
    @BindView(R.id.checkbox11)
    CheckBox checkBox11;
    @BindView(R.id.checkbox12)
    CheckBox checkBox12;

    @BindView(R.id.listview)
    SwipeMenuListView mListView;

    EquipmentDetailAdapter equipmentDetailAdapter;
    private List<EquipmentDetailVo> equipmentDetailVoList=new ArrayList<>();

    TimeSelector timeSelector;

    private long selectedFeedId;

//    private StringBuffer equipmentIds = new StringBuffer();
    Map<String,String> equipmentIdMap = new HashMap<>();
    Map<String,String> equipmentRealIdMap = new HashMap<>();
    private String  feedTime, grass;


    @Override
    protected int getLayout() {
        return R.layout.activity_feed_manage;
    }


    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        setToolBar(mToolBar, "喂养管理", "提交", R.mipmap.ic_left_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkFeeding()){
                    stateLoading();
                    Set<String> keys = equipmentRealIdMap.keySet();
                    StringBuffer equipmentIds = new StringBuffer();
                    for (String key : keys) {
                        equipmentIds.append(key+",");
                    }
                    mPresenter.feeding(equipmentIds.toString().substring(0,equipmentIds.toString().length()-1),String.valueOf(selectedFeedId),feedTime,grass);
                }
            }
        });

        timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String showTime,String paramTime) {
                feedTime = paramTime;
                mTvFeedTime.setText(showTime);
            }
        }, DateUtil.format(new Date(),"yyyy-MM-dd"), "2099-12-31");

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
                        String equipmentId = String.valueOf(equipmentDetailVo.getLivestock().getEquipmentId());
                        String equipmentNumber = equipmentRealIdMap.get(equipmentId);
                        equipmentRealIdMap.remove(equipmentId);

                        if(equipmentDetailVo.getParentEquipment()!=null) {
                            equipmentIdMap.remove(String.valueOf(equipmentDetailVo.getParentEquipment().getEquipmentNumber()));
                        }else{
                            equipmentIdMap.remove(equipmentNumber);
                        }
                        equipmentDetailAdapter.notifyDataSetChanged();
                        break;

                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }

    @OnClick(R.id.tv_feed_name)
    public void getFeedList(View view){
        FeedListActivity.open(FeedManageActivity.this,selectedFeedId);
    }

    @OnClick(R.id.btn_add_ear_tag)
    public void addEarTag(View view){
        startReadTag();
    }
    @Override
    public void getTagId(String tagId) {
        String equipmentId = tagId;
        if(equipmentIdMap.containsKey(equipmentId)){
            showErrorMsg("已扫描耳标"+equipmentId);
            return;
        }
        synchronized (this){
            stateLoading();
            mPresenter.getEquipmentDetailById(equipmentId);
        }
    }

    @OnClick(R.id.tv_feed_time)
    public void selectDate(View view){
//        timeSelector.setSelectedCalender(feedTime);
        timeSelector.show();
    }

    private boolean checkFeeding(){
        boolean isPassed = false;
        if(equipmentIdMap.isEmpty()){
            showErrorMsg("请录入耳标");
            return isPassed;
        }
        if(selectedFeedId == 0){
            showErrorMsg("请选择饲料");
            return isPassed;
        }
        grass = getGrass();
        if(StringUtil.isBlank(grass)){
            showErrorMsg("请选择牧草");
            return isPassed;
        }

        if(StringUtil.isBlank(feedTime)){
            showErrorMsg("请选择喂养日期");
            return isPassed;
        }

        isPassed = true;
        return isPassed;
    }



    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }



    @Override
    public void setEquipmentDetail(EquipmentDetailVo equipmentDetailVo,String equipmentId) {
        findViewById(R.id.ll_equipments).setVisibility(View.VISIBLE);
        equipmentDetailVoList.add(0,equipmentDetailVo);
        equipmentDetailAdapter.setDatas(equipmentDetailVoList);


        String equipRealId = equipmentDetailVo.getLivestock().getEquipmentId()+"";
        equipmentRealIdMap.put(equipRealId,equipmentId);

        equipmentIdMap.put(equipmentId,equipmentId);
    }

    @Override
    public void feedingSuccess() {
        //喂养成功
        equipmentIdMap.clear();
        equipmentDetailVoList.clear();
        equipmentDetailAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null){
            return;
        }
        FeedBean feedBean = (FeedBean) data.getSerializableExtra(Constants.SELECTED_FEED);
        if(feedBean!=null){
            selectedFeedId = feedBean.getId();
            mTvFeedName.setText(feedBean.getName());
        }
    }

    private String getGrass(){
        StringBuffer passSelected = new StringBuffer();
        if(checkBox1.isChecked()){
            passSelected.append(checkBox1.getText());
        }
        if(checkBox2.isChecked()){
            passSelected.append(","+checkBox2.getText());
        }
        if(checkBox3.isChecked()){
            passSelected.append(","+checkBox3.getText());
        }
        if(checkBox4.isChecked()){
            passSelected.append(","+checkBox4.getText());
        }
        if(checkBox5.isChecked()){
            passSelected.append(","+checkBox5.getText());
        }
        if(checkBox6.isChecked()){
            passSelected.append(","+checkBox6.getText());
        }
        if(checkBox7.isChecked()){
            passSelected.append(","+checkBox7.getText());
        }
        if(checkBox8.isChecked()){
            passSelected.append(","+checkBox8.getText());
        }
        if(checkBox9.isChecked()){
            passSelected.append(","+checkBox9.getText());
        }
        if(checkBox10.isChecked()){
            passSelected.append(","+checkBox10.getText());
        }
        if(checkBox11.isChecked()){
            passSelected.append(","+checkBox11.getText());
        }
        if(checkBox12.isChecked()){
            passSelected.append(","+checkBox12.getText());
        }
        String passStr = passSelected.toString();
        if(!StringUtil.isBlank(passStr)){
            passStr = passSelected.subSequence(1,passSelected.length()).toString();
        }
        return passStr;
    }
}
