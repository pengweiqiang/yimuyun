package com.yimuyun.lowraiseapp.presenter;


import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.base.RxPresenter;
import com.yimuyun.lowraiseapp.base.contract.UserContract;
import com.yimuyun.lowraiseapp.model.DataManager;
import com.yimuyun.lowraiseapp.model.bean.UserBean;
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

public class UserPresenter extends RxPresenter<UserContract.View> implements UserContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public UserPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void login(String userName, String password) {
        addSubscribe(mDataManager.login(userName, password, Constants.APP_NUMBER)
                .compose(RxUtil.<PadResultResponse<UserBean>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<UserBean>(mView, true) {
                    @Override
                    public void dataHandle(final UserBean userBean) {
                        //void getUserInfo(String phoneNumber);
                        mDataManager.setUserInstance(userBean);//存储全局 user 对象
                        setUserData(userBean);
                        addSubscribe(mDataManager.getUserInfo(userBean.getPhoneNumber())
                                .compose(RxUtil.<PadResultResponse<UserInfo>>rxSchedulerHelper())
                                .subscribeWith(new CommonSubscriber<UserInfo>(mView, true) {
                                    @Override
                                    public void dataHandle(UserInfo userInfo) {
                                        //void getUserInfo(String phoneNumber);
                                        userBean.setUserInfo(userInfo);
                                        mDataManager.setUserInstance(userBean);//存储全局 user 对象
                                        setUserData(userBean);
                                        mView.loginSuccess();
                                    }

                                    @Override
                                    public void onError(String msg) {
                                        super.onError(msg);
                                        mView.stateMain();
                                    }
                                }));

                    }

                    @Override
                    public void onError(String msg) {
                        super.onError(msg);
                        mView.stateMain();
                    }
                }));

    }

    /**
     * 添加PAD端需要的变量
     *
     * @param userBean
     */
    private void setUserData(UserBean userBean) {

    }


}
