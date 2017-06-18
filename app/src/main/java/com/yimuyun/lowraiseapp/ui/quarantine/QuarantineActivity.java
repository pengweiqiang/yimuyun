package com.yimuyun.lowraiseapp.ui.quarantine;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
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
import com.yimuyun.lowraiseapp.base.LowBaseRootActivity;
import com.yimuyun.lowraiseapp.base.contract.quarantine.QuarantineContract;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;
import com.yimuyun.lowraiseapp.presenter.QuarantinePresenter;
import com.yimuyun.lowraiseapp.ui.feed.EquipmentDetailAdapter;
import com.yimuyun.lowraiseapp.widget.GlideImageLoader;
import com.yimuyun.lowraiseapp.widget.GlideRoundTransform;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.helper.StringUtil;

import java.io.File;
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
 * @description 检疫证明
 * @Version
 */
public class QuarantineActivity extends LowBaseRootActivity<QuarantinePresenter> implements QuarantineContract.View{

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;


    @BindView(R.id.tv_picture)
    TextView mTvPicture;
    @BindView(R.id.iv_livestock_head)
    ImageView mIvHead;

    Map<String,String> equipmentIdMap = new HashMap<>();


    String quarantinePicture;
    long personnelId;


    @BindView(R.id.listview)
    SwipeMenuListView mListView;

    EquipmentDetailAdapter equipmentDetailAdapter;
    private List<EquipmentDetailVo> equipmentDetailVoList=new ArrayList<>();

    UploadManager uploadManager;
    @Override
    protected int getLayout() {
        return R.layout.activity_quarantine;
    }


    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        setToolBar(mToolBar, "检疫证明", "提交", R.mipmap.ic_left_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()) {
                    stateLoading();
                    uploadImage();
                }
            }
        });

        personnelId = App.getInstance().getUserBeanInstance().getPersonnelId();

        equipmentDetailAdapter = new EquipmentDetailAdapter(mContext,equipmentDetailVoList);
        mListView.setAdapter(equipmentDetailAdapter);
        mListView.addFooterView(new View(this));

        initMenuListView();
        initImagePicker();

        //构造一个带指定Zone对象的配置类
        Configuration config = new Configuration.Builder()
                .chunkSize(256 * 1024)  //分片上传时，每片的大小。 默认256K
                .putThreshhold(512 * 1024)  // 启用分片上传阀值。默认512K
                .connectTimeout(10) // 链接超时。默认10秒
                .responseTimeout(60) // 服务器响应超时。默认60秒
                .zone(com.qiniu.android.common.Zone.zone1) // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
                .build();
        uploadManager = new UploadManager(config);
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

        stateLoading();
        mPresenter.getEquipmentDetailById(equipmentId);
    }
    @OnClick({R.id.tv_picture,R.id.iv_livestock_head})
    public void selectOptions(View view){
        switch (view.getId()){
            case R.id.tv_picture:
                Intent intent = new Intent(this, ImageGridActivity.class);
                startActivityForResult(intent, Constants.REQUEST_IMAGE_PICKER);
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
                    this.quarantinePicture = picture;//TODO 上传图片
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
        }


    }

    private boolean checkInput(){
        boolean isPassed = false;


        if(StringUtil.isBlank(quarantinePicture)){
            showErrorMsg("请选择图片");
            return false;
        }
        if(equipmentIdMap.isEmpty()){
            showErrorMsg("请录入耳标");
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
    public void setEquipmentDetail(EquipmentDetailVo equipmentDetailVo, String equipmentId) {
        findViewById(R.id.ll_equipments).setVisibility(View.VISIBLE);
        equipmentDetailVoList.add(0,equipmentDetailVo);
        equipmentDetailAdapter.setDatas(equipmentDetailVoList);

        equipmentIdMap.put(equipmentId,equipmentId);
    }


    @Override
    public void insertQuarantineSuccess() {
        equipmentIdMap.clear();
        equipmentDetailVoList.clear();
        equipmentDetailAdapter.notifyDataSetChanged();

        quarantinePicture = "";
        mIvHead.setVisibility(View.INVISIBLE);
        mTvPicture.setHint("请选择图片");
    }

    private void uploadImage(){
        String key = null;
        Auth auth = Auth.create(Constants.accessKey, Constants.secretKey);
        String upToken = auth.uploadToken(Constants.bucket);
        uploadManager.put(new File(quarantinePicture), key, upToken,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info, JSONObject res) {
                        stateMain();
                        //res包含hash、key等信息，具体字段取决于上传策略的设置
                        if(info.isOK())
                        {
                            try {
                                String pictureKey = res.getString("key");
                                insertQuarantine(Constants.HOST_QINIU_URL+pictureKey);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            Log.i("qiniu", "Upload Success");
                        }
                        else{
                            Log.i("qiniu", "Upload Fail");
                            //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                        }
                        Log.i("qiniu", key + ",\r\n " + info + ",\r\n " + res);
                    }
                }, null);
    }
    private void insertQuarantine(String pictureUrl){
        Set<String> keys = equipmentIdMap.keySet();
        StringBuffer equipmentIds = new StringBuffer();
        for (String key : keys) {
            equipmentIds.append(key+",");
        }
        mPresenter.insertQuarantine(equipmentIds.toString().substring(0,equipmentIds.toString().length()-1),pictureUrl,personnelId);

    }
}
