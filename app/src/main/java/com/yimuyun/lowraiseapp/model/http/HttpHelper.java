package com.yimuyun.lowraiseapp.model.http;

import com.yimuyun.lowraiseapp.model.bean.UserBean;
import com.yimuyun.lowraiseapp.model.http.response.PadResultResponse;

import io.reactivex.Flowable;

/**
 * @date: 2017/5/04
 * @description:
 */

public interface HttpHelper {

    /**
     * 登陆
     *
     * @param userName
     * @param password
     * @return
     */
    Flowable<PadResultResponse<UserBean>> login(String userName, String password);


}
