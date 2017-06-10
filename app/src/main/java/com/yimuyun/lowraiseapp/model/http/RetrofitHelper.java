package com.yimuyun.lowraiseapp.model.http;


import com.yimuyun.lowraiseapp.model.bean.FeedVo;
import com.yimuyun.lowraiseapp.model.bean.UserBean;
import com.yimuyun.lowraiseapp.model.http.api.FeedApis;
import com.yimuyun.lowraiseapp.model.http.api.UserApis;
import com.yimuyun.lowraiseapp.model.http.response.PadResultResponse;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by pengweiqiang on 2017/05/08.
 */
public class RetrofitHelper implements HttpHelper {

    private UserApis mUserApisService;
    private FeedApis feedApis;

    @Inject
    public RetrofitHelper(UserApis userApisService,FeedApis feedApis) {
        this.mUserApisService = userApisService;
        this.feedApis = feedApis;

    }

    @Override
    public Flowable<PadResultResponse<UserBean>> login(String userName, String password) {
        return mUserApisService.login(userName, password);
    }

    @Override
    public Flowable<PadResultResponse<FeedVo>> feedList(String enterpriseId) {
        return feedApis.feedList(enterpriseId);
    }

    @Override
    public Flowable<PadResultResponse<Object>> feeding(String equipmentIds, String feedId, String feedTime, String grass) {
        return feedApis.feeding(equipmentIds, feedId, feedTime, grass);
    }

    @Override
    public Flowable<PadResultResponse<Object>> insertWeight(String equipmentId, String weighTime, String weighPhase, String cultureProcess, String weight, Long weighId) {
        return feedApis.insertWeight(equipmentId, weighTime, weighPhase, cultureProcess, weight, weighId);
    }


}
