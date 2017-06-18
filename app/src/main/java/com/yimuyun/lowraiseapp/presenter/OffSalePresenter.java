package com.yimuyun.lowraiseapp.presenter;


import com.yimuyun.lowraiseapp.base.RxPresenter;
import com.yimuyun.lowraiseapp.base.contract.offsale.OffSaleContract;
import com.yimuyun.lowraiseapp.model.DataManager;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;
import com.yimuyun.lowraiseapp.model.http.response.PadResultResponse;
import com.yimuyun.lowraiseapp.util.CommonSubscriber;
import com.yimuyun.lowraiseapp.util.RxUtil;

import javax.inject.Inject;

/**
 * @author will on 2017/5/4 21:55
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class OffSalePresenter extends RxPresenter<OffSaleContract.View> implements OffSaleContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public OffSalePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void insertOffSale(String equipmentIds, String salesTime, String customerName, String contactWay, String unitPrice, String weight) {
        addSubscribe(mDataManager.offlineSale(equipmentIds, salesTime, customerName, contactWay, unitPrice, weight)
                .compose(RxUtil.<PadResultResponse<Object>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<Object>(mView, true) {
                    @Override
                    public void dataHandle(Object body) {
                        if(body==null) {
                            mView.showErrorMsgToast("提交成功");
                            mView.insertOffSaleSuccess();
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        super.onError(msg);
                        mView.stateMain();
                    }
                }));
    }
    @Override
    public void getEquipmentDetailById(final String equipmentId) {
        addSubscribe(mDataManager.getEquimentInfoById(equipmentId)
                .compose(RxUtil.<PadResultResponse<EquipmentDetailVo>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<EquipmentDetailVo>(mView, true) {
                    @Override
                    public void dataHandle(EquipmentDetailVo equipmentDetailVo) {
                        if(equipmentDetailVo!=null) {
                            mView.setEquipmentDetail(equipmentDetailVo,equipmentId);
                        }else{
                            mView.showErrorMsgToast("耳标"+equipmentId+"查询为空");
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        super.onError(msg);
                        mView.stateMain();
                    }
                }));
    }
}
