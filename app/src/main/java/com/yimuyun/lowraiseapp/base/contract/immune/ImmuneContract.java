package com.yimuyun.lowraiseapp.base.contract.immune;


import com.yimuyun.lowraiseapp.base.BasePresenter;
import com.yimuyun.lowraiseapp.base.BaseView;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;

/**
 * @author will on 2017/6/6 21:57
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface ImmuneContract {
    interface View extends BaseView {
        void setEquipmentDetail(EquipmentDetailVo equipmentDetailVo, String equipmentId);
        void insertImmuneSuccess();


    }

    interface Presenter extends BasePresenter<ImmuneContract.View> {
        void getEquipmentDetailById(String equipmentId);
        void insertImmune(String equipmentIds,String immuneTime,
                          String vaccineType,String immuneWay,
                          String immunePersonnelId);

    }

}
