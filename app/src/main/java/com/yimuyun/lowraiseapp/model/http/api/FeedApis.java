package com.yimuyun.lowraiseapp.model.http.api;

import com.yimuyun.lowraiseapp.model.bean.FeedVo;
import com.yimuyun.lowraiseapp.model.http.response.PadResultResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
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
     * 饲料列表
     * @param enterpriseId
     * @return
     */
    @GET("culture/v1/feeding/feed/list")
    Flowable<PadResultResponse<FeedVo>> feedList(@Query("enterpriseId") String enterpriseId);

    //uid=1&token=2e53f0d9b84155c2daa1abd5735aad15&equipmentIds=1%2C2&feedId=1&feedTime=20170909&grass=%E5%90%84%E7%A7%8D%E8%8D%89
    @POST("culture/v1/feeding/feeding")
    Flowable<PadResultResponse<Object>> feeding(@Query("equipmentIds")String equipmentIds,@Query("feedId")String feedId,@Query("feedTime")String feedTime,@Query("grass")String grass);



    /**
     * 称重
     */
    @POST("culture/v1/weigh/insert")
    Flowable<PadResultResponse<Object>> insertWeight(@Query("equipmentId") String equipmentId,
                                                @Query("weighTime") String weighTime,@Query("weighPhase") String weighPhase,
                                                @Query("cultureProcess") String cultureProcess,@Query("weight") String weight,@Query("weighId") Long weighId);




}
