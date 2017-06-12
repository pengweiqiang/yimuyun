package com.yimuyun.lowraiseapp.base.contract.disinfect;


import com.yimuyun.lowraiseapp.base.BasePresenter;
import com.yimuyun.lowraiseapp.base.BaseView;

import java.util.List;

/**
 * @author will on 2017/6/6 21:57
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public interface DisinfectWayContract {
    interface View extends BaseView {
        void setDisifectWayList(List<String>disifectWayList);


    }

    interface Presenter extends BasePresenter<View> {
        void getDisifectWayList();
    }

}
