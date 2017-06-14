package com.yimuyun.lowraiseapp.base.contract.quarantine;


import com.yimuyun.lowraiseapp.base.BasePresenter;
import com.yimuyun.lowraiseapp.base.BaseView;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;

/**
 * @author will on 2017/6/6 21:57
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface QuarantineContract {
    interface View extends BaseView {
        void setEquipmentDetail(EquipmentDetailVo equipmentDetailVo, String equipmentId);
        void insertQuarantineSuccess();


    }

    interface Presenter extends BasePresenter<View> {
        void getEquipmentDetailById(final String equipmentId);
        void insertQuarantine(String equipmentIds, String quarantinePicture,
                                long personnelId);

    }

}
