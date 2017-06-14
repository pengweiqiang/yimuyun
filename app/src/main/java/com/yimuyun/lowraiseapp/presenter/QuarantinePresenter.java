package com.yimuyun.lowraiseapp.presenter;


import com.yimuyun.lowraiseapp.base.RxPresenter;
import com.yimuyun.lowraiseapp.base.contract.quarantine.QuarantineContract;
import com.yimuyun.lowraiseapp.model.DataManager;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;
import com.yimuyun.lowraiseapp.model.http.response.PadResultResponse;
import com.yimuyun.lowraiseapp.util.CommonSubscriber;
import com.yimuyun.lowraiseapp.util.RxUtil;
import com.yimuyun.lowraiseapp.util.ToastUtil;

import javax.inject.Inject;

/**
 * @author will on 2017/5/4 21:55
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class QuarantinePresenter extends RxPresenter<QuarantineContract.View> implements QuarantineContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public QuarantinePresenter(DataManager mDataManager) {
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
    public void insertQuarantine(String equipmentIds, String quarantinePicture, long personnelId) {
        addSubscribe(mDataManager.insertQuarantine(equipmentIds, quarantinePicture, personnelId)
                .compose(RxUtil.<PadResultResponse<Object>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<Object>(mView, true) {
                    @Override
                    public void dataHandle(Object body) {
                        if(body==null) {
                            mView.showErrorMsgToast("提交成功");
                            mView.insertQuarantineSuccess();
                        }else{
                            ToastUtil.show("查询结果为空");
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
