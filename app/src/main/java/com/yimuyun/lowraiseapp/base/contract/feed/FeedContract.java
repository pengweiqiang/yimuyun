package com.yimuyun.lowraiseapp.base.contract.feed;


import com.yimuyun.lowraiseapp.base.BasePresenter;
import com.yimuyun.lowraiseapp.base.BaseView;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;

/**
 * @author will on 2017/6/6 21:57
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface FeedContract {
    interface View extends BaseView {
        void setEquipmentDetail(EquipmentDetailVo equipmentDetailVo,String equipmentId);
        void feedingSuccess();

    }

    interface Presenter extends BasePresenter<View> {
        void feeding(String equipmentIds,String feedId,String feedTime,String grass);

        void getEquipmentDetailById(String equipmentId);
    }

}
