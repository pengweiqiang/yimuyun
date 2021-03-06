package com.yimuyun.lowraiseapp.base.contract.onlinesale;


import com.yimuyun.lowraiseapp.base.BasePresenter;
import com.yimuyun.lowraiseapp.base.BaseView;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;

/**
 * @author will on 2017/6/6 21:57
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface OnlineSaleContract {
    interface View extends BaseView {

        void setEquipmentDetail(EquipmentDetailVo equipmentDetailVo, String equipmentId);
        void insertOnlineSaleSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getEquipmentDetailById(String equipmentId);
        void insertOnLineSale(String equipmentIds);

    }

}
