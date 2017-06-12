package com.yimuyun.lowraiseapp.base.contract.purchase;


import com.yimuyun.lowraiseapp.base.BasePresenter;
import com.yimuyun.lowraiseapp.base.BaseView;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;

/**
 * @author will on 2017/6/6 21:57
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface PurchaseContract {
    interface View extends BaseView {
        void setUserInfo(UserInfo userInfo);
        void setEquipmentDetail(EquipmentDetailVo equipmentDetailVo, String equipmentId);
        void insertSaleBuySuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getUserInfo(String phoneNumber);
        void getEquipmentDetailById(String equipmentId);
        void insertSaleBuy(String equipmentIds,String enterpriseId,
                           String personnelId);

    }

}
