package com.yimuyun.lowraiseapp.presenter;


import com.yimuyun.lowraiseapp.base.RxPresenter;
import com.yimuyun.lowraiseapp.base.contract.disinfect.DisinfectWayContract;
import com.yimuyun.lowraiseapp.model.DataManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author will on 2017/5/4 21:55
 * @email pengweiqiang64@163.com
 * @description
 * @Version
 */

public class DisinfectWayPresenter extends RxPresenter<DisinfectWayContract.View> implements DisinfectWayContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public DisinfectWayPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getDisifectWayList() {
        List<String> disifectWayList = new ArrayList<>();
        disifectWayList.add("熏蒸");
        disifectWayList.add("喷洒");
        disifectWayList.add("浸泡");
        mView.setDisifectWayList(disifectWayList);
    }
}
