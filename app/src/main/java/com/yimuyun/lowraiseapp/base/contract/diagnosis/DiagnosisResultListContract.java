package com.yimuyun.lowraiseapp.base.contract.diagnosis;


import com.yimuyun.lowraiseapp.base.BasePresenter;
import com.yimuyun.lowraiseapp.base.BaseView;
import com.yimuyun.lowraiseapp.model.bean.DiagnosisResultBean;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;

import java.util.List;

/**
 * @author will on 2017/6/6 21:57
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface DiagnosisResultListContract {
    interface View extends BaseView {
        void setUserInfo(UserInfo userInfo);
        void setDiagnosisResult(List<DiagnosisResultBean> diagnosisResultBeanList);

    }

    interface Presenter extends BasePresenter<View> {

        void getUserInfo(String phoneNumber);
        void getDiagnosisResultList(String enterpriseId);

    }

}
