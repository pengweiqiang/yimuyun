package com.yimuyun.lowraiseapp.base.contract.diagnosis;


import com.yimuyun.lowraiseapp.base.BasePresenter;
import com.yimuyun.lowraiseapp.base.BaseView;
import com.yimuyun.lowraiseapp.model.bean.DrugBean;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;

import java.util.List;

/**
 * @author will on 2017/6/6 21:57
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface DrugListContract {
    interface View extends BaseView {
        void setUserInfo(UserInfo userInfo);
        void setDrugList(List<DrugBean> drugList);

    }

    interface Presenter extends BasePresenter<View> {

        void getUserInfo(String phoneNumber);
        void getDrugList(String enterpriseId,String type);

    }

}
