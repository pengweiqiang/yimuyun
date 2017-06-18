package com.yimuyun.lowraiseapp.base.contract.weight;


import com.yimuyun.lowraiseapp.base.BasePresenter;
import com.yimuyun.lowraiseapp.base.BaseView;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;
import com.yimuyun.lowraiseapp.model.bean.WeightVo;

/**
 * @author will on 2017/6/6 21:57
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface WeightContract {
    interface View extends BaseView {
        void setWeightList(WeightVo weightVo);

        void insertWeightSuccess();

        void setLivestockInfo(EquipmentDetailVo equipmentDetailVo);

    }

    interface Presenter extends BasePresenter<View> {
        void getLivestockInfo(String equipmentId);
        void getWeightList(String equipmentId);
        void insertWeight(String equipmentId,
                          String weighTime,String weighPhase,
                          String cultureProcess,String weight,Long weighId);
    }

}
