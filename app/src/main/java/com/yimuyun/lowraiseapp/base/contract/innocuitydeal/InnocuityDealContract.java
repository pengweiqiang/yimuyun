package com.yimuyun.lowraiseapp.base.contract.innocuitydeal;


import com.yimuyun.lowraiseapp.base.BasePresenter;
import com.yimuyun.lowraiseapp.base.BaseView;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;

/**
 * @author will on 2017/6/6 21:57
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface InnocuityDealContract {
    interface View extends BaseView {
        void setLiveStockInfo(EquipmentDetailVo equipmentDetailVo);

        void insertDisposalSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getLivestockInfo(String equipmentId);

        void insertDisposal(String morbidityTime,String disposalTime,
                            String deathTime,String deathReason,
                            String personnelId,String equipmentId,
                            String disposalMethod);
    }

}
