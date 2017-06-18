package com.yimuyun.lowraiseapp.presenter;


import com.yimuyun.lowraiseapp.base.RxPresenter;
import com.yimuyun.lowraiseapp.base.contract.immune.ImmuneContract;
import com.yimuyun.lowraiseapp.model.DataManager;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;
import com.yimuyun.lowraiseapp.model.bean.EquipmentInfoVo;
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

public class ImmunePresenter extends RxPresenter<ImmuneContract.View> implements ImmuneContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public ImmunePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void insertImmune(String equipmentIds, String immuneTime, String vaccineType, String immuneWay, String immunePersonnelId) {
        addSubscribe(mDataManager.insertImmune(equipmentIds, immuneTime, vaccineType, immuneWay, immunePersonnelId)
                .compose(RxUtil.<PadResultResponse<Object>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<Object>(mView, true) {
                    @Override
                    public void dataHandle(Object body) {
                        if(body==null) {
                            mView.showErrorMsgToast("提交成功");
                            mView.insertImmuneSuccess();
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
        addSubscribe(mDataManager.getEquipmentInfoByNumber(equipmentId)
                .compose(RxUtil.<PadResultResponse<EquipmentInfoVo>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<EquipmentInfoVo>(mView, true) {
                    @Override
                    public void dataHandle(EquipmentInfoVo equipmentInfoVo) {
                        if(equipmentInfoVo!=null) {

                            int equipId = equipmentInfoVo.getEquipment().getId();
                            addSubscribe(mDataManager.getEquimentInfoById(equipId+"")
                                    .compose(RxUtil.<PadResultResponse<EquipmentDetailVo>>rxSchedulerHelper())
                                    .subscribeWith(new CommonSubscriber<EquipmentDetailVo>(mView, true) {
                                        @Override
                                        public void dataHandle(EquipmentDetailVo equipmentDetailVo) {
                                            if(equipmentDetailVo!=null) {
                                                mView.setEquipmentDetail(equipmentDetailVo,equipmentId);
                                            }else{
                                                mView.showErrorMsg("耳标"+equipmentId+"查询结果为空");
                                            }
                                        }

                                        @Override
                                        public void onError(String msg) {
                                            super.onError(msg);
                                            mView.stateMain();
                                        }
                                    }));

                        }else{
                            mView.showErrorMsg("耳标"+equipmentId+"信息查询为空");
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
