package com.yimuyun.lowraiseapp.ui.neweartag;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.util.Auth;
import com.yimuyun.lowraiseapp.R;
import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.base.RootActivity;
import com.yimuyun.lowraiseapp.base.contract.neweartag.NewEarTagContract;
import com.yimuyun.lowraiseapp.model.bean.EquipmentInfoVo;
import com.yimuyun.lowraiseapp.model.bean.Personnel;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;
import com.yimuyun.lowraiseapp.model.bean.Varieties;
import com.yimuyun.lowraiseapp.presenter.NewEarTagPresenter;
import com.yimuyun.lowraiseapp.ui.LowGetEarTagActivity;
import com.yimuyun.lowraiseapp.ui.UserListActivity;
import com.yimuyun.lowraiseapp.ui.disinfect.DisinfectWayListAdapter;
import com.yimuyun.lowraiseapp.util.DateUtil;
import com.yimuyun.lowraiseapp.util.NumberFormatCheckUtils;
import com.yimuyun.lowraiseapp.widget.GlideImageLoader;
import com.yimuyun.lowraiseapp.widget.GlideRoundTransform;
import com.yimuyun.lowraiseapp.widget.MsgAlertDialog;
import com.yimuyun.lowraiseapp.widget.MyPopupWindow;
import com.yimuyun.lowraiseapp.widget.TimeSelector;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.helper.StringUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.rb_xieyang)
    RadioButton mRbXieyang;

    @BindView(R.id.tv_is_pregnancy)
    TextView mTvIsPregnancy;
    @BindView(R.id.tv_picture)
    TextView mTvPicture;
    @BindView(R.id.iv_livestock_head)
    ImageView mIvHead;



    private String enterpriseId;

    String equipmentId;
    private String equipmentNumber;
    String livestockMasterId,livestockName;
    String type = "0";//牛0，羊1
    String state ="03";//状态 00饲养 01屠宰 02检疫不通过 03线上销售 04线下销售
    String initialWeight;
    String initialTime; String lairageWeight;
    String initialShowTime;String lairageShowTime;
    String lairageTime; String birthplace; String varietiesId,varietiesName;
    String sex = "1";//性别 公1，母0
    String isPregnancy = "2"; //是否已孕 1已孕，2未怀孕
    String picture = "";
    String parentEquipmentId;
    String parentEquipmentNumber;

    TimeSelector birthDayTimeSelector;
    TimeSelector lairageTimeSelector;

    @Override
    protected int getLayout() {
        return R.layout.activity_new_ear_tag;
    }

    UploadManager uploadManager;

    @Override
    protected void initEventAndData() {
        setToolBar(mToolBar, "新建耳标", "提交", R.mipmap.ic_left_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()){
                    stateLoading();
                    uploadImage();
                }
            }
        });

        equipmentNumber = getIntent().getStringExtra(Constants.EQUPIMENT_ID);
        birthDayTimeSelector = new TimeSelector(mContext, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String showTime, String paramTime) {
                initialTime = paramTime;
                initialShowTime = showTime;
                mTvBirthDayTime.setText(showTime);
            }
        },"1989-01-01","2099-12-30");

        lairageTimeSelector = new TimeSelector(mContext, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String showTime, String paramTime) {
                lairageTime = paramTime;
                lairageShowTime = showTime;
                mTvLairageTime.setText(showTime);
            }
        },"2000-01-01","2099-12-30");

//        mTvParentEquipmentId.setText(equipmentNumber);
        mTvEquipmentId.setText(equipmentNumber);

        mRbCow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    type = "0";
//                    mRbMale.setChecked(true);
                    mRbXieyang.setText("犍牛");
                }
            }
        });

        mRbSheep.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    type = "1";
//                    mRbMale.setChecked(true);
                    mRbXieyang.setText("羯羊");
                }
            }
        });
        mRbFemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sex = "0";
                    mTvIsPregnancy.setVisibility(View.VISIBLE);
                }
            }
        });
        mRbXieyang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sex = "2";
                    isPregnancy = "2";
                    mTvIsPregnancy.setVisibility(View.INVISIBLE);
                }
            }
        });

        mRbMale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sex = "1";
                    isPregnancy = "2";
                    mTvIsPregnancy.setVisibility(View.INVISIBLE);
                }
            }
        });

        stateLoading();
        mPresenter.getEquipmentInfoByNumber(equipmentNumber,false);
        mPresenter.getUserInfo(App.getInstance().getUserBeanInstance().getPhoneNumber());


        initImagePicker();


        //构造一个带指定Zone对象的配置类
        Configuration config = new Configuration.Builder()
//                .chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认256K
//                .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认512K
                .connectTimeout(10) // 链接超时。默认10秒
                .responseTimeout(60) // 服务器响应超时。默认60秒
                .zone(com.qiniu.android.common.Zone.zone1) // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
                .build();
        uploadManager = new UploadManager(config);

        initCacheView();
    }

    public void initCacheView(){
        try {
            Map<String, Object> cacheData = mPresenter.getLastSelectData();
            if (cacheData != null) {
//            params.put("type",type);
//            params.put("state",state);
//            params.put("initialWeight",initialWeight);
//            params.put("initialTime",initialTime);
//            params.put("lairageWeight",lairageWeight);
//            params.put("lairageTime",lairageTime);
//            params.put("birthplace",birthplace);
//            params.put("varietiesId",varietiesId);
//            params.put("sex",sex);
//            params.put("isPregnancy",isPregnancy);
                if (cacheData.containsKey("initialWeight")) {
                    livestockMasterId = (String) cacheData.get("livestockMasterId");
                    livestockName = (String) cacheData.get("livestockName");
                    initialWeight = (String) cacheData.get("initialWeight");
                    initialTime = (String) cacheData.get("initialTime");
                    initialShowTime = (String)cacheData.get("initialShowTime");
                    lairageWeight = (String) cacheData.get("lairageWeight");
                    lairageTime = (String) cacheData.get("lairageTime");
                    lairageShowTime = (String)cacheData.get("lairageShowTime");
                    birthplace = (String) cacheData.get("birthplace");
                    type = (String) cacheData.get("type");
                    varietiesId = (String) cacheData.get("varietiesId");
                    varietiesName = (String) cacheData.get("varietiesName");
                    sex = (String) cacheData.get("sex");
                    isPregnancy = (String) cacheData.get("isPregnancy");
                    if ("0".equals(type)) {//牛
                        mRbCow.setChecked(true);
                        mRbXieyang.setText("犍牛");
                    } else {
                        mRbSheep.setChecked(true);
                        mRbXieyang.setText("羯羊");
                    }
                    if ("0".equals(sex)) {
                        mRbFemale.setChecked(true);
                    } else if ("1".equals(sex)) {
                        mRbMale.setChecked(true);
                    } else {
                        mRbXieyang.setChecked(true);
                    }
                    if ("1".equals(isPregnancy)) {//已怀孕
                        mTvIsPregnancy.setText("怀孕");
                    } else {
                        mTvIsPregnancy.setText("未怀孕");
                    }
                    mEtInitWeight.setText(initialWeight);
                    mEtLairageWeight.setText(lairageWeight);
                    mEtBirthdayAddress.setText(birthplace);
                    mTvLairageTime.setText(lairageShowTime);
                    mTvBirthDayTime.setText(initialShowTime);
                    mTvVarieties.setText(varietiesName);
                    mTvPersonnel.setText(livestockName);

                }
            }
        }catch (Exception e){

        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        initEventAndData();
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

    @OnClick({R.id.tv_birthday_time,R.id.tv_lairage_time,R.id.ll_parent_equipment})
    public void selectBirthDaytime(View view){
        switch (view.getId()){
            case R.id.tv_birthday_time:
                birthDayTimeSelector.show();
                break;
            case R.id.tv_lairage_time:
                lairageTimeSelector.show();
                break;
            case R.id.ll_parent_equipment://母体耳标
                LowGetEarTagActivity.open(NewEarTagManageActivity.this,NewEarTagManageActivity.class.getSimpleName(),"2");
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
                mPresenter.getVarietiesList(enterpriseId,type);
                break;
            case R.id.tv_deal_personnel:
                UserListActivity.open(NewEarTagManageActivity.this,"选择畜主");
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
                    this.picture = picture;
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
            livestockName = personnel.getName();
            livestockMasterId = personnel.getId()+"";
        }else if(requestCode == Constants.REQUEST_PARENT_EQUIPMENT_ID){//母体耳标
            String parentEquipmentNumber = data.getStringExtra(Constants.EQUPIMENT_ID);
            mTvParentEquipmentId.setText(parentEquipmentNumber);
            stateLoading();
            mPresenter.getEquipmentInfoByNumber(parentEquipmentNumber,false);
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
//        parentEquipmentId = mTvParentEquipmentId.getText().toString().trim();
//        if(StringUtil.isBlank(parentEquipmentId)){
//            showErrorMsg("母体编号为空");
//            return false;
//        }
//        parentEquipmentId = equipmentId;
        //TODO 重新读取父的EquipmentNumber
        if(StringUtil.isBlank(type)){
            showErrorMsg("请选择类型");
            return false;
        }
        if(StringUtil.isBlank(varietiesId)){
            showErrorMsg("请选择品种");
            return false;
        }
        varietiesName = mTvVarieties.getText().toString().trim();
        if(StringUtil.isBlank(sex)){
            showErrorMsg("请选择性别");
            return false;
        }
        initialWeight = mEtInitWeight.getText().toString().trim();
        if(StringUtil.isBlank(initialWeight)){
            mEtInitWeight.requestFocus();
            showErrorMsg("请输入出生重量");
            return false;
        }
        if(!NumberFormatCheckUtils.checkNumber(initialWeight)){
            mEtInitWeight.requestFocus();
            showErrorMsg("请填写正确的出生重量");
            return false;
        }
        lairageWeight = mEtLairageWeight.getText().toString().trim();
        if(StringUtil.isBlank(lairageWeight)){
            mEtLairageWeight.requestFocus();
            showErrorMsg("请输入入栏重量");
            return false;
        }
        if(!NumberFormatCheckUtils.checkNumber(lairageWeight)){
            mEtLairageWeight.requestFocus();
            showErrorMsg("请填写正确的入栏重量");
            return false;
        }
        if(Double.valueOf(initialWeight)>Double.valueOf(lairageWeight)){
            showErrorMsg("出生重量不能大于入栏重量");
            return false;
        }
        if(StringUtil.isBlank(initialTime) || StringUtil.isBlank(lairageTime)){
            showErrorMsg("请选择时间");
            return false;
        }
        if(!StringUtil.isBlank(initialTime) && !StringUtil.isBlank(lairageTime)){
            if(DateUtil.compareAfterDate(initialTime,lairageTime,"yyyyMMdd")){
                showErrorMsgToast("出生时间和入栏时间选择错误");
                return false;
            }
        }
        birthplace = mEtBirthdayAddress.getText().toString();
        if(StringUtil.isBlank(birthplace)){
            showErrorMsg("请输入出生地");
            mEtBirthdayAddress.requestFocus();
            return false;
        }
        if(StringUtil.isBlank(picture)){
            showErrorMsg("请选择图片");
            return false;
        }




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

    private void uploadImage(){
        String key = null;
        Auth auth = Auth.create(Constants.accessKey, Constants.secretKey);
        String upToken = auth.uploadToken(Constants.bucket);
        uploadManager.put(new File(picture), key, upToken,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info, JSONObject res) {
                        stateMain();
                        //res包含hash、key等信息，具体字段取决于上传策略的设置
                        if(info.isOK())
                        {
                            try {
                                String pictureKey = Constants.HOST_QINIU_URL+res.getString("key");
                                insertLiveStock(pictureKey);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        else{
                            Log.i("qiniu", "Upload Fail");
                            //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                        }
                    }
                }, null);
    }

    @Override
    public void insertLiveStockSuccess() {
        showCommitSuccessDialog();
    }

    @Override
    public void setEquipmentId(EquipmentInfoVo equipmentInfoVo,boolean isParent) {
        if(isParent){
            parentEquipmentId = equipmentInfoVo.getEquipment().getId()+"";
        }else {
            equipmentId = equipmentInfoVo.getEquipment().getId() + "";
        }
    }

    private void insertLiveStock(String url){
        stateLoading();
        mPresenter.insertLiveStock(enterpriseId,equipmentId, livestockMasterId,livestockName, type, state, initialWeight, initialTime,initialShowTime, lairageWeight, lairageTime,lairageShowTime, birthplace, varietiesId,varietiesName, sex, isPregnancy, url, parentEquipmentId);
    }

    private void showCommitSuccessDialog(){
        final MsgAlertDialog msgAlertDialog = new MsgAlertDialog(mContext);
        msgAlertDialog.show();
        msgAlertDialog.setMsgText("是否继续新建耳标？");
        msgAlertDialog.setConfirmOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgAlertDialog.dismiss();
                LowGetEarTagActivity.open(mContext,NewEarTagManageActivity.class.getName());
            }
        });
        msgAlertDialog.setCancelText("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgAlertDialog.dismiss();
                finish();
            }
        });
    }
}
