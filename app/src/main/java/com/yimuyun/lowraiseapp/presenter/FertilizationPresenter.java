package com.yimuyun.lowraiseapp.presenter;


import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.base.RxPresenter;
import com.yimuyun.lowraiseapp.base.contract.fertilization.FertilizationContract;
import com.yimuyun.lowraiseapp.model.DataManager;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;
import com.yimuyun.lowraiseapp.model.bean.EquipmentInfoVo;
import com.yimuyun.lowraiseapp.model.bean.FertilizationBean;
import com.yimuyun.lowraiseapp.model.bean.UserBean;
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

public class FertilizationPresenter extends RxPresenter<FertilizationContract.View> implements FertilizationContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public FertilizationPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getLiveStockInfo(final String equipmentId) {
        addSubscribe(mDataManager.getEquipmentInfoByNumber(equipmentId)
                .compose(RxUtil.<PadResultResponse<EquipmentInfoVo>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<EquipmentInfoVo>(mView, true) {
                    @Override
                    public void dataHandle(EquipmentInfoVo equipmentInfoVo) {
                        if(equipmentInfoVo!=null) {

                            //耳标授权企业 start
                            if(equipmentInfoVo.getApplicationEquipment()!=null){
                                int applicationEnterPriseId = equipmentInfoVo.getApplicationEquipment().getEnterpriseId();
                                UserBean userBean = App.getInstance().getUserBeanInstance();
                                if(userBean.getUserInfo()!=null && applicationEnterPriseId!=userBean.getUserInfo().getEnterprise().getId()){
                                    mView.showErrorMsg("该耳标没有授权给该企业"+applicationEnterPriseId);
                                    return;
                                }
                            }
                            //耳标授权企业 end

                            int equipId = equipmentInfoVo.getEquipment().getId();
                            addSubscribe(mDataManager.getEquimentInfoById(equipId+"")
                                    .compose(RxUtil.<PadResultResponse<EquipmentDetailVo>>rxSchedulerHelper())
                                    .subscribeWith(new CommonSubscriber<EquipmentDetailVo>(mView, true) {
                                        @Override
                                        public void dataHandle(EquipmentDetailVo equipmentDetailVo) {
                                            if(equipmentDetailVo!=null) {
                                                mView.setLivestockInfo(equipmentDetailVo);
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

    @Override
    public void updatePregnancy(String equipmentId, String type) {
        addSubscribe(mDataManager.transFertilization(equipmentId, type)
                .compose(RxUtil.<PadResultResponse<FertilizationBean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<FertilizationBean>(mView, true) {
                    @Override
                    public void dataHandle(FertilizationBean fertilizationBean) {
                        if(fertilizationBean!=null) {
                            ToastUtil.show("提交成功");
                            mView.updatePreganacySuccess();
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
