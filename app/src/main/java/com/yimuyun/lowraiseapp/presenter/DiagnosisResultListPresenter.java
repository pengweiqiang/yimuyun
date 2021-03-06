package com.yimuyun.lowraiseapp.presenter;


import com.yimuyun.lowraiseapp.base.RxPresenter;
import com.yimuyun.lowraiseapp.base.contract.diagnosis.DiagnosisResultListContract;
import com.yimuyun.lowraiseapp.model.DataManager;
import com.yimuyun.lowraiseapp.model.bean.DiagnosisResultVo;
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

public class DiagnosisResultListPresenter extends RxPresenter<DiagnosisResultListContract.View> implements DiagnosisResultListContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public DiagnosisResultListPresenter(DataManager mDataManager) {
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
    public void getDiagnosisResultList(String enterpriseId) {
        addSubscribe(mDataManager.getDiagnosisResultList(enterpriseId)
                .compose(RxUtil.<PadResultResponse<DiagnosisResultVo>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<DiagnosisResultVo>(mView, true) {
                    @Override
                    public void dataHandle(DiagnosisResultVo diagnosisResultVo) {
                        if(diagnosisResultVo!=null && diagnosisResultVo.getDiagnosisResults()!=null && !diagnosisResultVo.getDiagnosisResults().isEmpty()) {
                            mView.setDiagnosisResult(diagnosisResultVo.getDiagnosisResults());
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
