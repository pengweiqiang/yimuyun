package com.yimuyun.lowraiseapp.model;


import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.model.bean.UserBean;
import com.yimuyun.lowraiseapp.model.db.DBHelper;
import com.yimuyun.lowraiseapp.model.http.HttpHelper;
import com.yimuyun.lowraiseapp.model.http.response.PadResultResponse;
import com.yimuyun.lowraiseapp.model.prefs.PreferencesHelper;

import io.reactivex.Flowable;

/**
 * @author: Est <codeest.dev@gmail.com>
 * @date: 2017/4/21
 * @desciption:
 */

public class DataManager implements HttpHelper, DBHelper, PreferencesHelper {

    HttpHelper mHttpHelper;
    DBHelper mDbHelper;
    PreferencesHelper mPreferencesHelper;

    public DataManager(HttpHelper httpHelper, DBHelper dbHelper, PreferencesHelper preferencesHelper) {
        mHttpHelper = httpHelper;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
    }

    @Override
    public boolean getNightModeState() {
        return mPreferencesHelper.getNightModeState();
    }

    @Override
    public void setNightModeState(boolean state) {
        mPreferencesHelper.setNightModeState(state);
    }

    @Override
    public boolean getNoImageState() {
        return mPreferencesHelper.getNoImageState();
    }

    @Override
    public void setUserInstance(UserBean userBean) {
        App.getInstance().setUserInstance(userBean);
        mPreferencesHelper.setUserInstance(userBean);
    }


    @Override
    public UserBean getUserInstance() {
        return mPreferencesHelper.getUserInstance();
    }

    @Override
    public Flowable<PadResultResponse<UserBean>> login(String userName, String password) {
        return mHttpHelper.login(userName,password);
    }
}
