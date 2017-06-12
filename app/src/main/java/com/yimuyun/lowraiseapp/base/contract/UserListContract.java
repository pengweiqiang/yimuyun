package com.yimuyun.lowraiseapp.base.contract;


import com.yimuyun.lowraiseapp.base.BasePresenter;
import com.yimuyun.lowraiseapp.base.BaseView;
import com.yimuyun.lowraiseapp.model.bean.Personnel;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;

import java.util.List;

/**
 * @author will on 2017/6/6 21:57
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface UserListContract {
    interface View extends BaseView {
        void setUserInfo(UserInfo userInfo);
        void setUserList(List<Personnel> personnelList);


    }

    interface Presenter extends BasePresenter<View> {
        void getUserInfo(String phoneNumber);
        void getUserList(String enterpriseId);
    }

}
