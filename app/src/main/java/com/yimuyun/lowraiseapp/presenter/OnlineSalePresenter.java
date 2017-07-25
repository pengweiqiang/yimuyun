package com.yimuyun.lowraiseapp.presenter;


import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.base.RxPresenter;
import com.yimuyun.lowraiseapp.base.contract.onlinesale.OnlineSaleContract;
import com.yimuyun.lowraiseapp.model.DataManager;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;
import com.yimuyun.lowraiseapp.model.bean.EquipmentInfoVo;
import com.yimuyun.lowraiseapp.model.bean.UserBean;
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
//        addSubscribe(mDataManager.getEquimentInfoById(equipmentId)
//                .compose(RxUtil.<PadResultResponse<EquipmentDetailVo>>rxSchedulerHelper())
//                .subscribeWith(new CommonSubscriber<EquipmentDetailVo>(mView, true) {
//                    @Override
//                    public void dataHandle(EquipmentDetailVo equipmentDetailVo) {
//                        if(equipmentDetailVo!=null) {
//                            mView.setEquipmentDetail(equipmentDetailVo,equipmentId);
//                        }else{
//                            mView.showErrorMsgToast("耳标"+equipmentId+"查询为空");
//                        }
//                    }
//
//                    @Override
//                    public void onError(String msg) {
//                        super.onError(msg);
//                        mView.stateMain();
//                    }
//                }));
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
                                    mView.showErrorMsg("该耳标没有授权给该企业");
                                    return;
                                }
                            }
                            //耳标授权企业 end

                            String equipId = equipmentInfoVo.getEquipment().getId()+"";
                            addSubscribe(mDataManager.getEquimentInfoById(equipId)
                                    .compose(RxUtil.<PadResultResponse<EquipmentDetailVo>>rxSchedulerHelper())
                                    .subscribeWith(new CommonSubscriber<EquipmentDetailVo>(mView, true) {
                                        @Override
                                        public void dataHandle(EquipmentDetailVo equipmentDetailVo) {
                                            if(equipmentDetailVo!=null) {
                                                if(equipmentDetailVo.getLivestock()!=null){
                                                    equipmentDetailVo.getLivestock().setEquipmentNumberApp(equipmentId);
                                                }
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
