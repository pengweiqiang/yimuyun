package com.yimuyun.lowraiseapp.base.contract.neweartag;


import com.yimuyun.lowraiseapp.base.BasePresenter;
import com.yimuyun.lowraiseapp.base.BaseView;
import com.yimuyun.lowraiseapp.model.bean.EquipmentInfoVo;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;
import com.yimuyun.lowraiseapp.model.bean.Varieties;

import java.util.List;

/**
 * @author will on 2017/6/6 21:57
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface NewEarTagContract {
    interface View extends BaseView {
        void setUserInfo(UserInfo userInfo);
        void setVarietiesList(List<Varieties> varietiesList);

        void insertLiveStockSuccess();

        void setEquipmentId(EquipmentInfoVo equipmentInfoVo,boolean isParent);

    }

    interface Presenter extends BasePresenter<View> {
        void getEquipmentInfoByNumber(String equipmentNumber,boolean isParent);

        void getUserInfo(String phoneNumber);
        //获取种类
        void getVarietiesList(String enterpriseId);

        void insertLiveStock(String enterpriseId,String equipmentId,String livestockMasterId,
                             String type,String state,
                             String initialWeight,String initialTime,
                             String lairageWeight,String lairageTime,
                             String birthplace,String varietiesId,
                             String sex,String isPregnancy,
                             String picture,String parentEquipmentId);
    }

}
