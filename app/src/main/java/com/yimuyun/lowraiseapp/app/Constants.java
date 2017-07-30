package com.yimuyun.lowraiseapp.app;

import android.os.Environment;

import java.io.File;

/**
 * Created by codeest on 2016/8/3.
 */
public class Constants {


    //================= TYPE ====================


    public static final String HOST_URL = "http://115.28.109.174:8383/yimu/";
//    public static final String HOST_URL = "http://192.168.31.36:8080/";

    public static final String APP_NUMBER = "yy0011";//低频养殖端

    public static final String HOST_QINIU_URL = "http://oriloodon.bkt.clouddn.com/";

    public static final String accessKey = "5MdtfOaShfxW6Kw_AtNOnP3vDfVFGMNrR7U7OMDW";
    public static final String secretKey = "zA2vnNlt5bAVhGFL3-OxaOXDlGbFW71kB-o2vduQ";
    public static final String bucket = "yimu";

    //================= KEY ====================


    public static final String BUGLY_ID = "1495dc91d1";//BUGLY 官网申请





    public static final int PLUGIN_VALID = 0;
    public static final int PLUGIN_NOT_INSTALLED = -1;
    public static final int PLUGIN_NEED_UPGRADE = 2;


    //================= KEY ====================
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_FAIL = -1;
    public static final int CODE_EXCEPTION = 1;
    public static final int CODE_INVALID_TOKEN = 2;//无效的token,需要重新登录
    public static final String CODE_EMPTY_DATA = "EMPTY";

    public static final int START_PAGE = 0;//开始页码
    public static final int PAGE_SIZE = 10;//每页显示条数

    public static final int SEX_FEMALE= 0;//母
    public static final int SEX_MALE = 1;//公


    //================= PATH ====================

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "yimuyun" + File.separator + "yimuyunapp";

    //================= PREFERENCE ====================

    public static final String SP_NIGHT_MODE = "night_mode";

    public static final String SP_NO_IMAGE = "no_image";

    public static final String SP_AUTO_CACHE = "auto_cache";

    public static final String SP_CURRENT_ITEM = "current_item";

    public static final String SP_LIKE_POINT = "like_point";

    public static final String SP_VERSION_POINT = "version_point";

    public static final String SP_MANAGER_POINT = "manager_point";

    public static final String SP_USER_INSTANCE_STR = "";

    public static final String SP_NEW_EAR_TAG_CACHE = "new_ear_tag_cache";

    //================= INTENT ====================
    public static final String ID = "id";
    public static final String TYPE = "type";
    public static final String SELECTED_FEED = "selectedFeed";
    public static final String EQUPIMENT_ID = "equpimentId";
    public static final String PARENT_EQUIPMENT_ID = "parentEquipmentId";
    public static final String SELECTED_USER_ITEM = "selectedUser";
    public static final String ADD_DIAGNOSI_RECORD = "diagnosisTreatment";
    public static final String SELECTED_DIAGNOSIS_RESULT = "selectedDiagnosisResult";
    public static final String SELECTED_DIAGNOSIS_TREATMENT_PLAN = "selectedDiagnosisTreatmentPlan";
    public static final String SELECTED_DRUG = "selectedDrug";
    public static final String SELECTED_DISINFECT_NAME = "selectedDisinfectName";
    public static final String ACTION_BAR_TITLE = "actionBarTitle";
    public static final String ACTIVITY_NAME = "activityName";



    public static final String USER_NAME = "userName";



    /*------------------------------activity request code----------------------------------*/
    public static final int REQUEST_FEED_LIST = 1001;
    public static final int REQUEST_USER_ITEM = 1002;
    public static final int REQUEST_ADD_DIAGNOSIS_RECORD = 1003;
    public static final int REQUEST_ADD_DIAGNOSIS_TREATMENT_PLAN = 1004;
    public static final int REQUEST_DRUG = 1005;
    public static final int REQUEST_DISINFECT = 1006;
    public static final int REQUEST_IMAGE_PICKER = 1007;
    public static final int REQUEST_PARENT_EQUIPMENT_ID = 1008;



    /*------------------------------activity result code----------------------------------*/

}
