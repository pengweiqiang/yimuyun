package com.yimuyun.lowraiseapp.presenter;


import com.yimuyun.lowraiseapp.base.RxPresenter;
import com.yimuyun.lowraiseapp.base.contract.diagnosis.DiagnosisContract;
import com.yimuyun.lowraiseapp.model.DataManager;
import com.yimuyun.lowraiseapp.model.bean.DiagnosisTreatmentVo;
import com.yimuyun.lowraiseapp.model.bean.EquipmentDetailVo;
import com.yimuyun.lowraiseapp.model.http.response.PadResultResponse;
import com.yimuyun.lowraiseapp.util.CommonSubscriber;
import com.yimuyun.lowraiseapp.util.RxUtil;
import com.yimuyun.lowraiseapp.util.ToastUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * @author will on 2017/5/4 21:55
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class DiagnosisPresenter extends RxPresenter<DiagnosisContract.View> implements DiagnosisContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public DiagnosisPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getLiveStockInfo(String equipmentId) {
        addSubscribe(mDataManager.getEquimentInfoById(equipmentId)
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
    }

    @Override
    public void getDiagnosisTreatment(String equipmentId) {
        addSubscribe(mDataManager.getDiagnosisTreatmentList(equipmentId)
                .compose(RxUtil.<PadResultResponse<List<DiagnosisTreatmentVo>>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<List<DiagnosisTreatmentVo>>(mView, true) {
                    @Override
                    public void dataHandle(List<DiagnosisTreatmentVo> diagnosisTreatmentVoList) {
                        mView.setDiagnosisTreatment(diagnosisTreatmentVoList);
                    }
                    @Override
                    public void onError(String msg) {
                        super.onError(msg);
                        mView.stateMain();
                    }
                }));
    }

    @Override
    public void insertDiagnosisTreatment(String equipmentId,String personnalId, String treatmentPlanId, String symptoms, String result, String dragId, String time) {
        addSubscribe(mDataManager.insertDiagnosisTreatment(equipmentId,personnalId, treatmentPlanId, symptoms, result, dragId, time)
                .compose(RxUtil.<PadResultResponse<Object>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<Object>(mView, true) {
                    @Override
                    public void dataHandle(Object body) {
                        if(body==null) {
                            mView.showErrorMsgToast("提交成功");
                            mView.insertDiagnosisTreatmentSuccess();
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
