package com.yimuyun.lowraiseapp.base.contract.fertilization;


import com.yimuyun.lowraiseapp.base.BasePresenter;
import com.yimuyun.lowraiseapp.base.BaseView;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;

/**
 * @author will on 2017/6/6 21:57
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface FertilizationContract {
    interface View extends BaseView {
        void setLivestockInfo(EquipmentDetailVo equipmentDetailVo);

        void updatePreganacySuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getLiveStockInfo(String equipmentId);


        void updatePregnancy(String equipmentId,String type);

    }

}
