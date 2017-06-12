package com.yimuyun.lowraiseapp.presenter;


import com.yimuyun.lowraiseapp.base.RxPresenter;
import com.yimuyun.lowraiseapp.base.contract.onlinesale.OnlineSaleContract;
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

public class OnlineSalePresenter extends RxPresenter<OnlineSaleContract.View> implements OnlineSaleContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public OnlineSalePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
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
    public void insertOnLineSale(String equipmentIds) {
        addSubscribe(mDataManager.publishSale(equipmentIds)
                .compose(RxUtil.<PadResultResponse<Object>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<Object>(mView, true) {
                    @Override
                    public void dataHandle(Object body) {
                        if(body==null) {
                            mView.showErrorMsgToast("提交成功");
                            mView.insertOnlineSaleSuccess();
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
