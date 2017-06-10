package com.yimuyun.lowraiseapp.model.http;


import com.yimuyun.lowraiseapp.model.bean.UserBean;
import com.yimuyun.lowraiseapp.model.http.api.UserApis;
import com.yimuyun.lowraiseapp.model.http.response.PadResultResponse;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by pengweiqiang on 2017/05/08.
 */
public class RetrofitHelper implements HttpHelper {

    private UserApis mUserApisService;

    @Inject
    public RetrofitHelper(UserApis userApisService) {
        this.mUserApisService = userApisService;

    }

    @Override
    public Flowable<PadResultResponse<UserBean>> login(String userName, String password) {
        return mUserApisService.login(userName, password);
    }


}
