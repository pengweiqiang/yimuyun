package com.yimuyun.lowraiseapp.presenter.home;

import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.base.RxPresenter;
import com.yimuyun.lowraiseapp.base.contract.home.WelcomeContract;
import com.yimuyun.lowraiseapp.model.bean.UserBean;
import com.yimuyun.lowraiseapp.util.RxUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * @author will on 2017/6/6 09:56
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class WelcomePresenter extends RxPresenter<WelcomeContract.View> implements WelcomeContract.Presenter {

    private static final int COUNT_DOWN_TIME = 2200;//欢迎界面等待时间

    @Inject
    public WelcomePresenter() {
    }

    @Override
    public void startMainActivity() {
        startCountDown();
    }

    @Override
    public void checkIsLogin() {
        UserBean userBean = App.getInstance().getUserBeanInstance();
        if(userBean==null){
            mView.jumpToLogin();
        }else {
            mView.jumpToHome();
        }
    }

    private void startCountDown() {
        addSubscribe(Flowable.timer(COUNT_DOWN_TIME, TimeUnit.MILLISECONDS)
                .compose(RxUtil.<Long>rxSchedulerHelper())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        checkIsLogin();
                    }
                }));
    }



}
