package com.yimuyun.lowraiseapp.base.contract;


import com.yimuyun.lowraiseapp.base.BasePresenter;
import com.yimuyun.lowraiseapp.base.BaseView;

/**
 * @author will on 2017/6/6 21:57
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface UserContract {
    interface View extends BaseView {
        void loginSuccess();


    }

    interface Presenter extends BasePresenter<View> {
        void login(String userName, String password);

    }

}
