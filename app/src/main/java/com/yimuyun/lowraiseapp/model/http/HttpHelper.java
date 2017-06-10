package com.yimuyun.lowraiseapp.model.http;

import com.yimuyun.lowraiseapp.model.bean.FeedVo;
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

    //饲料
    Flowable<PadResultResponse<FeedVo>> feedList(String enterpriseId);

    //喂养
    Flowable<PadResultResponse<Object>> feeding(String equipmentIds, String feedId, String feedTime, String grass);

    //称重
    Flowable<PadResultResponse<Object>> insertWeight(String equipmentId,
                                                String weighTime,String weighPhase,
                                                String cultureProcess,String weight,Long weighId);






}
