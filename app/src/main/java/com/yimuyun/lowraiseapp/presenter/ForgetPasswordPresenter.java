package com.yimuyun.lowraiseapp.presenter;


import com.yimuyun.lowraiseapp.base.RxPresenter;
import com.yimuyun.lowraiseapp.base.contract.ForgetPasswordContract;
import com.yimuyun.lowraiseapp.model.DataManager;
import com.yimuyun.lowraiseapp.model.bean.CodeBean;
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

public class ForgetPasswordPresenter extends RxPresenter<ForgetPasswordContract.View> implements ForgetPasswordContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public ForgetPasswordPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }




    @Override
    public void getCode(String phoneNumber) {
        addSubscribe(mDataManager.getRegisteVerifyCode(phoneNumber)
                .compose(RxUtil.<PadResultResponse<CodeBean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<CodeBean>(mView, true) {
                    @Override
                    public void dataHandle(CodeBean codeBean) {
                        mView.setCode(codeBean.getCode());
                        mView.resetCode();
                    }

                    @Override
                    public void onError(String msg) {
                        super.onError(msg);
                        mView.stateMain();
                        mView.resetCode();
                    }
                }));
    }

    @Override
    public void forgetPassword(String mobile, String verifyCode, String password) {
        addSubscribe(mDataManager.changepassword(mobile, verifyCode, password)
                .compose(RxUtil.<PadResultResponse<Object>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<Object>(mView, true) {
                    @Override
                    public void dataHandle(Object body) {
                        if(body!=null){
                            mView.showErrorMsgToast(body.toString());
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
