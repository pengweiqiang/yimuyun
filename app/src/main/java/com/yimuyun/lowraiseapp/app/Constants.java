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
//    public static final String HOST_URL = "http://172.16.101.216:8080/";
//    public static final String HOST_URL = "http://172.16.100.133:8080/";

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

    //================= INTENT ====================
    public static final String BUY_SHOW_BACK_BTN = "isShowBtn";

    public static final String SKU = "sku";

    public static final String CITY_NAME = "cityName";

    //第一级分类sid
    public static final String FIRST_CATEGORY_SID = "firstCategorySid";

    //末级分类sid
    public static final String LAST_CATEGORY_SID = "lastCategorySid";

    //品类名称
    public static final String CATEGORY_NAME = "categoryName";

    public static final String CATEGORY_SIDS = "categorySids";

    public static final String USER_NAME = "userName";

    public static final String IT_GOLD_TYPE = "type";

    public static final String IT_GOLD_TYPE_STR = "type_str";

    public static final String IT_GOLD_MANAGER = "manager";

    public static final String IT_VTEX_TYPE = "type";

    public static final String IT_VTEX_TOPIC_ID = "id";

    public static final String IT_VTEX_REPLIES_TOP = "top_info";

    public static final String IT_VTEX_NODE_NAME = "node_name";

    /*------------------------------activity request code----------------------------------*/




    /*------------------------------activity result code----------------------------------*/

}
