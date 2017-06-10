package com.yimuyun.lowraiseapp.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.model.bean.UserBean;

import javax.inject.Inject;

/**
 * @date: 2017/4/21
 * @description:
 */

public class ImplPreferencesHelper implements PreferencesHelper {

    private static final boolean DEFAULT_NIGHT_MODE = false;
    private static final boolean DEFAULT_NO_IMAGE = false;
    private static final boolean DEFAULT_AUTO_SAVE = true;

    private static final boolean DEFAULT_LIKE_POINT = false;
    private static final boolean DEFAULT_VERSION_POINT = false;
    private static final boolean DEFAULT_MANAGER_POINT = false;

//    private static final int DEFAULT_CURRENT_ITEM = Constants.TYPE_ZHIHU;

    private static final String SHAREDPREFERENCES_NAME = "my_sp";

    private final SharedPreferences mSPrefs;

    @Inject
    public ImplPreferencesHelper() {
        mSPrefs = App.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public boolean getNightModeState() {
        return mSPrefs.getBoolean(Constants.SP_NIGHT_MODE, DEFAULT_NIGHT_MODE);
    }

    @Override
    public void setNightModeState(boolean state) {
        mSPrefs.edit().putBoolean(Constants.SP_NIGHT_MODE, state).apply();
    }

    @Override
    public boolean getNoImageState() {
        return mSPrefs.getBoolean(Constants.SP_NO_IMAGE, DEFAULT_NO_IMAGE);
    }

    @Override
    public void setUserInstance(UserBean userBean) {
        if (userBean != null) {
            mSPrefs.edit().putString(Constants.SP_USER_INSTANCE_STR, new Gson().toJson(userBean)).apply();
        } else {
            mSPrefs.edit().putString(Constants.SP_USER_INSTANCE_STR, "").apply();
        }
    }

    @Override
    public UserBean getUserInstance() {
        UserBean userBean = null;
        String userBeanStr = mSPrefs.getString(Constants.SP_USER_INSTANCE_STR, null);
        if (userBeanStr != null) {
            userBean = new Gson().fromJson(userBeanStr, UserBean.class);
        }
        return userBean;
    }


//    @Override
//    public void setNoImageState(boolean state) {
//        mSPrefs.edit().putBoolean(Constants.SP_NO_IMAGE, state).apply();
//    }
//
//    @Override
//    public boolean getAutoCacheState() {
//        return mSPrefs.getBoolean(Constants.SP_AUTO_CACHE, DEFAULT_AUTO_SAVE);
//    }
//
//    @Override
//    public void setAutoCacheState(boolean state) {
//        mSPrefs.edit().putBoolean(Constants.SP_AUTO_CACHE, state).apply();
//    }

//    @Override
//    public int getCurrentItem() {
//        return mSPrefs.getInt(Constants.SP_CURRENT_ITEM, DEFAULT_CURRENT_ITEM);
//    }
//
//    @Override
//    public void setCurrentItem(int item) {
//        mSPrefs.edit().putInt(Constants.SP_CURRENT_ITEM, item).apply();
//    }
//
//    @Override
//    public boolean getLikePoint() {
//        return mSPrefs.getBoolean(Constants.SP_LIKE_POINT, DEFAULT_LIKE_POINT);
//    }
//
//    @Override
//    public void setLikePoint(boolean isFirst) {
//        mSPrefs.edit().putBoolean(Constants.SP_LIKE_POINT, isFirst).apply();
//    }
//
//    @Override
//    public boolean getVersionPoint() {
//        return mSPrefs.getBoolean(Constants.SP_VERSION_POINT, DEFAULT_VERSION_POINT);
//    }
//
//    @Override
//    public void setVersionPoint(boolean isFirst) {
//        mSPrefs.edit().putBoolean(Constants.SP_VERSION_POINT, isFirst).apply();
//    }
//
//    @Override
//    public boolean getManagerPoint() {
//        return mSPrefs.getBoolean(Constants.SP_MANAGER_POINT, DEFAULT_MANAGER_POINT);
//    }
//
//    @Override
//    public void setManagerPoint(boolean isFirst) {
//        mSPrefs.edit().putBoolean(Constants.SP_MANAGER_POINT, isFirst).apply();
//    }
}
