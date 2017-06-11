package com.yimuyun.lowraiseapp.base.contract.enviromentmonitor;


import com.yimuyun.lowraiseapp.base.BasePresenter;
import com.yimuyun.lowraiseapp.base.BaseView;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;

/**
 * @author will on 2017/6/6 21:57
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface EnvironmentMonitorContract {
    interface View extends BaseView {

        void setUserInfo(UserInfo userInfo);

       void insertEnvironmentSucecess();
    }

    interface Presenter extends BasePresenter<View> {

        void getUserInfo(String phoneNumber);

        void insertEnvironment(String enterpriseId,String beam,
                               String temperature,String humidity,
                               String so2,String co2,String h2s,
                               String nh3,String ch4);

    }

}
