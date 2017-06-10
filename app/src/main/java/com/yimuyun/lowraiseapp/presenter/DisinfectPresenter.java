package com.yimuyun.lowraiseapp.presenter;


import com.yimuyun.lowraiseapp.base.RxPresenter;
import com.yimuyun.lowraiseapp.base.contract.feed.FeedContract;
import com.yimuyun.lowraiseapp.model.DataManager;
import com.yimuyun.lowraiseapp.model.bean.FeedVo;
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

public class DisinfectPresenter extends RxPresenter<FeedContract.View> implements FeedContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public DisinfectPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }


    @Override
    public void getFeedList(String enterpriseId) {
        addSubscribe(mDataManager.feedList(enterpriseId)
                .compose(RxUtil.<PadResultResponse<FeedVo>>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<FeedVo>(mView, true) {
                    @Override
                    public void dataHandle(FeedVo feedVo) {
                        if(feedVo!=null) {
                            mView.setFeedList(feedVo.getFeeds());
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
    public void feeding(String equipmentIds, String feedId, String feedTime, String grass) {

    }
}
