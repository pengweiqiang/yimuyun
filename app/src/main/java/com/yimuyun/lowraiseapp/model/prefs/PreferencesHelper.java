package com.yimuyun.lowraiseapp.model.prefs;


import com.yimuyun.lowraiseapp.model.bean.UserBean;


public interface PreferencesHelper {

    boolean getNightModeState();

    void setNightModeState(boolean state);

    boolean getNoImageState();

    void setUserInstance(UserBean userBean);

    UserBean getUserInstance();

//    void setNoImageState(boolean state);
//
//    boolean getAutoCacheState();
//
//    void setAutoCacheState(boolean state);
//
//    int getCurrentItem();
//
//    void setCurrentItem(int item);
//
//    boolean getLikePoint();
//
//    void setLikePoint(boolean isFirst);
//
//    boolean getVersionPoint();
//
//    void setVersionPoint(boolean isFirst);
//
//    boolean getManagerPoint();
//
//    void setManagerPoint(boolean isFirst);

}
