package com.yimuyun.lowraiseapp.presenter;


import com.yimuyun.lowraiseapp.base.RxPresenter;
import com.yimuyun.lowraiseapp.base.contract.diagnosis.DiagnosisTreatmentPlanListContract;
import com.yimuyun.lowraiseapp.model.DataManager;
import com.yimuyun.lowraiseapp.model.bean.DiagnosisTreatmentPlanVo;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;
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

public class DiagnosisTreatmentPlanListPresenter extends RxPresenter<DiagnosisTreatmentPlanListContract.View> implements DiagnosisTreatmentPlanListContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public DiagnosisTreatmentPlanListPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getUserInfo(String phoneNumber) {
        addSubscribe(mDataManager.getUserInfo(phoneNumber)
                .compose(RxUtil.<PadResultResponse<UserInfo>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<UserInfo>(mView, true) {
                    @Override
                    public void dataHandle(UserInfo userInfo) {
                        if(userInfo!=null) {
                            mView.setUserInfo(userInfo);
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
    public void getDiagnosisTreatmentPlanList(String enterpriseId) {
        addSubscribe(mDataManager.getDiagnosisTreatmentPlanList(enterpriseId)
                .compose(RxUtil.<PadResultResponse<DiagnosisTreatmentPlanVo>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<DiagnosisTreatmentPlanVo>(mView, true) {
                    @Override
                    public void dataHandle(DiagnosisTreatmentPlanVo diagnosisTreatmentPlanVo) {
                        if(diagnosisTreatmentPlanVo!=null && diagnosisTreatmentPlanVo.getDiagnosisTreatmentPlans()!=null && !diagnosisTreatmentPlanVo.getDiagnosisTreatmentPlans().isEmpty()) {
                            mView.setDiagnosisTreatmentPlanList(diagnosisTreatmentPlanVo.getDiagnosisTreatmentPlans());
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
