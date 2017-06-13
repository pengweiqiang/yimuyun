package com.yimuyun.lowraiseapp.ui.neweartag;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.base.RootActivity;
import com.yimuyun.lowraiseapp.base.contract.neweartag.NewEarTagContract;
import com.yimuyun.lowraiseapp.model.bean.Personnel;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;
import com.yimuyun.lowraiseapp.model.bean.Varieties;
import com.yimuyun.lowraiseapp.presenter.NewEarTagPresenter;
import com.yimuyun.lowraiseapp.ui.UserListActivity;
import com.yimuyun.lowraiseapp.ui.disinfect.DisinfectWayListAdapter;
import com.yimuyun.lowraiseapp.widget.GlideImageLoader;
import com.yimuyun.lowraiseapp.widget.GlideRoundTransform;
import com.yimuyun.lowraiseapp.widget.MyPopupWindow;
import com.yimuyun.lowraiseapp.widget.TimeSelector;

import org.jsoup.helper.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author will on 2017/6/10 12:14
 * @email pengweiqiang64@163.com
 * @description 新建耳标
 * @Version
 */
public class NewEarTagManageActivity extends RootActivity<NewEarTagPresenter> implements NewEarTagContract.View{

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    @BindView(R.id.tv_parent_equipmentid)
    TextView mTvParentEquipmentId;
    @BindView(R.id.tv_equipmentid)
    TextView mTvEquipmentId;

    @BindView(R.id.tv_birthday_time)
    TextView mTvBirthDayTime;
    @BindView(R.id.tv_lairage_time)
    TextView mTvLairageTime;

    @BindView(R.id.tv_varieties)
    TextView mTvVarieties;
    @BindView(R.id.tv_deal_personnel)
    TextView mTvPersonnel;

    @BindView(R.id.et_birthday_address)
    EditText mEtBirthdayAddress;
    @BindView(R.id.et_lairage_weight)
    EditText mEtLairageWeight;
    @BindView(R.id.et_init_weight)
    EditText mEtInitWeight;

    @BindView(R.id.rb_cow)
    RadioButton mRbCow;
    @BindView(R.id.rb_sheep)
    RadioButton mRbSheep;
    @BindView(R.id.rb_female)
    RadioButton mRbFemale;
    @BindView(R.id.rb_male)
    RadioButton mRbMale;

    @BindView(R.id.tv_is_pregnancy)
    TextView mTvIsPregnancy;
    @BindView(R.id.tv_picture)
    TextView mTvPicture;
    @BindView(R.id.iv_livestock_head)
    ImageView mIvHead;


    private String enterpriseId;

    String equipmentId;
    String livestockMasterId;
    String type = "0";//牛0，羊1
    String state ="03";//状态 00饲养 01屠宰 02检疫不通过 03线上销售 04线下销售
    String initialWeight;
    String initialTime; String lairageWeight;
    String lairageTime; String birthplace; String varietiesId;
    String sex = "1";//性别 公1，母0
    String isPregnancy = "2"; //是否已孕 1已孕，2未怀孕
    String picture = "";
    String parentEquipmentId;

    TimeSelector birthDayTimeSelector;
    TimeSelector lairageTimeSelector;

    @Override
    protected int getLayout() {
        return R.layout.activity_new_ear_tag;
    }


    @Override
    protected void initEventAndData() {
        setToolBar(mToolBar, "新建耳标", "提交", R.mipmap.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()){
                    stateLoading();
                    mPresenter.insertLiveStock(enterpriseId,equipmentId, livestockMasterId, type, state, initialWeight, initialTime, lairageWeight, lairageTime, birthplace, varietiesId, sex, isPregnancy, picture, parentEquipmentId);
                }
            }
        });

        equipmentId = getIntent().getStringExtra(Constants.EQUPIMENT_ID);
        birthDayTimeSelector = new TimeSelector(mContext, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String showTime, String paramTime) {
                initialTime = paramTime;
                mTvBirthDayTime.setText(showTime);
            }
        },"1989-01-01","2099-12-30");

        lairageTimeSelector = new TimeSelector(mContext, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String showTime, String paramTime) {
                lairageTime = paramTime;
                mTvLairageTime.setText(showTime);
            }
        },"2000-01-01","2099-12-30");

        mTvParentEquipmentId.setText(equipmentId);
        mTvEquipmentId.setText(equipmentId);

        mRbCow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    type = "0";
                }
            }
        });

        mRbSheep.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    type = "1";
                }
            }
        });
        mRbFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sex = "1";
                    mTvIsPregnancy.setVisibility(View.VISIBLE);
                }
            }
        });

        mRbMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sex = "0";
                    isPregnancy = "2";
                    mTvIsPregnancy.setVisibility(View.INVISIBLE);
                }
            }
        });

        stateLoading();
        mPresenter.getUserInfo(App.getInstance().getUserBeanInstance().getPhoneNumber());

        initImagePicker();
    }

    private void initImagePicker(){
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }

    @OnClick({R.id.tv_birthday_time,R.id.tv_lairage_time})
    public void selectBirthDaytime(View view){
        switch (view.getId()){
            case R.id.tv_birthday_time:
                birthDayTimeSelector.show();
                break;
            case R.id.tv_lairage_time:
                lairageTimeSelector.show();
                break;
        }
    }

    @OnClick({R.id.tv_varieties,R.id.tv_deal_personnel,R.id.tv_is_pregnancy,R.id.tv_picture,R.id.iv_livestock_head})
    public void selectOptions(View view){
        switch (view.getId()){
            case R.id.tv_varieties:
                if(varietiesList!=null && !varietiesList.isEmpty()){
                    showVarietiesView();
                    return;
                }
                stateLoading();
                mPresenter.getVarietiesList(enterpriseId);
                break;
            case R.id.tv_deal_personnel:
                UserListActivity.open(NewEarTagManageActivity.this);
                break;
            case R.id.tv_is_pregnancy:
                showPregancyView();
                break;
            case R.id.tv_picture:
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, Constants.REQUEST_IMAGE_PICKER);
//                showSelectPicture();
                break;
            case R.id.iv_livestock_head:
                Intent intentPicture = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intentPicture, Constants.REQUEST_IMAGE_PICKER);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null){
            return;
        }
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == Constants.REQUEST_IMAGE_PICKER) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if(images!=null && !images.isEmpty()) {
                    String picture = images.get(0).path;
                    this.picture = picture;//TODO 上传图片
                    Glide.with(mContext).load(picture).placeholder(R.mipmap.ic_default_head).//加载中显示的图片
                            error(new ColorDrawable(mContext.getResources().getColor(R.color.color_line_grey)))//加载失败时显示的图片
                            .transform(new GlideRoundTransform(mContext,5))
//                            .centerCrop()
                            .into(mIvHead);
                    mIvHead.setVisibility(View.VISIBLE);
                    mTvPicture.setHint("");
                }

            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode == Constants.REQUEST_USER_ITEM){
            Personnel personnel = (Personnel)data.getSerializableExtra(Constants.SELECTED_USER_ITEM);
            mTvPersonnel.setText(personnel.getName());
            livestockMasterId = personnel.getId()+"";
        }


    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    private boolean checkInput(){
        boolean isPassed = true;
        if(StringUtil.isBlank(equipmentId)){
            showErrorMsg("获取耳标为空");
            return false;
        }
        parentEquipmentId = mTvParentEquipmentId.getText().toString().trim();
        if(StringUtil.isBlank(parentEquipmentId)){
            showErrorMsg("母体编号为空");
            return false;
        }
        if(StringUtil.isBlank(type)){
            showErrorMsg("请选择类型");
            return false;
        }
        if(StringUtil.isBlank(varietiesId)){
            showErrorMsg("请选择品种");
            return false;
        }
        if(StringUtil.isBlank(sex)){
            showErrorMsg("请选择性别");
            return false;
        }
        birthplace = mEtBirthdayAddress.getText().toString();
        if(StringUtil.isBlank(birthplace)){
            showErrorMsg("请输入出生地");
            mEtBirthdayAddress.requestFocus();
            return false;
        }
        initialWeight = mEtInitWeight.getText().toString().trim();
        lairageWeight = mEtLairageWeight.getText().toString().trim();


        return isPassed ;
    }
    @Override
    public void setUserInfo(UserInfo userInfo) {
        enterpriseId = userInfo.getPersonnel().getEnterpriseId()+"";
    }

    PopupWindow mVarietisPopupWindow;
    List<Varieties> varietiesList;
    @Override
    public void setVarietiesList(final List<Varieties> varietiesList) {
        this.varietiesList = varietiesList;
        showVarietiesView();
    }
    private void showVarietiesView(){
        mVarietisPopupWindow = MyPopupWindow.getPopupWindow(R.layout.popup_varities_view,NewEarTagManageActivity.this,R.style.popupAnimation,true,true,0);
        mVarietisPopupWindow.showAtLocation(mTvVarieties, Gravity.BOTTOM, 0,0);
        ListView mListView = (ListView)mVarietisPopupWindow.getContentView().findViewById(R.id.listview);
        List<String> varietiesNameList = new ArrayList<>();
        for(Varieties varieties:varietiesList){
            varietiesNameList.add(varieties.getName());
        }
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                varietiesId = varietiesList.get(position).getId()+"";
                mTvVarieties.setText(varietiesList.get(position).getName());
                mVarietisPopupWindow.dismiss();
            }
        });
        DisinfectWayListAdapter disinfectWayListAdapter = new DisinfectWayListAdapter(mContext,varietiesNameList,"");
        mListView.setAdapter(disinfectWayListAdapter);
        mVarietisPopupWindow.getContentView().findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVarietisPopupWindow.dismiss();
            }
        });
    }
    PopupWindow mPregancyPopupWindow;
    private void showPregancyView(){
        mPregancyPopupWindow = MyPopupWindow.getPopupWindow(R.layout.popup_varities_view,NewEarTagManageActivity.this,R.style.popupAnimation,true,true,0);
        mPregancyPopupWindow.showAtLocation(mTvVarieties, Gravity.BOTTOM, 0,0);
        ListView mListView = (ListView)mPregancyPopupWindow.getContentView().findViewById(R.id.listview);
        final List<String> pregancyList = new ArrayList<>();
        pregancyList.add("未孕");//2
        pregancyList.add("已孕");//1

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    isPregnancy = "2";
                }else if(position == 1){
                    isPregnancy = "1";
                }

                mTvIsPregnancy.setText(pregancyList.get(position));
                mPregancyPopupWindow.dismiss();
            }
        });
        DisinfectWayListAdapter disinfectWayListAdapter = new DisinfectWayListAdapter(mContext,pregancyList,"");
        mListView.setAdapter(disinfectWayListAdapter);
        mPregancyPopupWindow.getContentView().findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPregancyPopupWindow.dismiss();
            }
        });
    }

    PopupWindow mPicturePopupWindow;
    private void showSelectPicture(){
        mPicturePopupWindow = MyPopupWindow.getPopupWindow(R.layout.popup_varities_view,NewEarTagManageActivity.this,R.style.popupAnimation,true,true,0);
        mPicturePopupWindow.showAtLocation(mTvVarieties, Gravity.BOTTOM, 0,0);
        ListView mListView = (ListView)mPicturePopupWindow.getContentView().findViewById(R.id.listview);
        final List<String> itemList = new ArrayList<>();
        itemList.add("拍照");//2
        itemList.add("从相册中选择");//1

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){

                }else if(position == 1){
                    isPregnancy = "1";
                }


                mPicturePopupWindow.dismiss();
            }
        });
        DisinfectWayListAdapter disinfectWayListAdapter = new DisinfectWayListAdapter(mContext,itemList,"");
        mListView.setAdapter(disinfectWayListAdapter);
        mPicturePopupWindow.getContentView().findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPicturePopupWindow.dismiss();
            }
        });
    }

    @Override
    public void insertLiveStockSuccess() {

    }
}
