package com.yimuyun.lowraiseapp.model.http.api;

import com.yimuyun.lowraiseapp.model.bean.UserBean;
import com.yimuyun.lowraiseapp.model.http.response.PadResultResponse;

import io.reactivex.Flowable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author will on 2017/6/10 14:29
 * @email pengweiqiang64@163.com
 * @description 饲养端
 * @Version
 */

public interface FeedApis {

    /**
     * 称重
     */
    @POST("/culture/v1/weigh/insert")
    Flowable<PadResultResponse<UserBean>> login(@Query("equipmentId") String equipmentId,
                                                @Query("weighTime") String weighTime,@Query("weighPhase") String weighPhase,
                                                @Query("cultureProcess") String cultureProcess,@Query("weight") String weight,@Query("weighId") Long weighId);




}
