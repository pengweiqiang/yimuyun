package com.yimuyun.lowraiseapp.presenter;


import com.yimuyun.lowraiseapp.base.RxPresenter;
import com.yimuyun.lowraiseapp.base.contract.UserListContract;
import com.yimuyun.lowraiseapp.model.DataManager;
import com.yimuyun.lowraiseapp.model.bean.PersonnelVo;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;
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

public class UserListPresenter extends RxPresenter<UserListContract.View> implements UserListContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public UserListPresenter(DataManager mDataManager) {
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
    public void getUserList(String enterpriseId) {
        addSubscribe(mDataManager.getUserList(enterpriseId)
                .compose(RxUtil.<PadResultResponse<PersonnelVo>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<PersonnelVo>(mView, true) {
                    @Override
                    public void dataHandle(PersonnelVo personnelVo) {
                        if(personnelVo!=null){
                            mView.setUserList(personnelVo.getPersonnels());
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
