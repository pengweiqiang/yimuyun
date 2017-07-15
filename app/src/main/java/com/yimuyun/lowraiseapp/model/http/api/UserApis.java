package com.yimuyun.lowraiseapp.model.http.api;


import com.yimuyun.lowraiseapp.model.bean.CodeBean;
import com.yimuyun.lowraiseapp.model.bean.PersonnelVo;
import com.yimuyun.lowraiseapp.model.bean.UserBean;
import com.yimuyun.lowraiseapp.model.bean.UserInfo;
import com.yimuyun.lowraiseapp.model.http.response.PadResultResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author will on 2017/5/2 16:50
 * @email pengweiqiang64@163.com
 * @description 用户相关api
 * @Version
 */

public interface UserApis {

    /**
     * 用户登录
     */
    @POST("culture/v1/user/login")
    Flowable<PadResultResponse<UserBean>> login(@Query("phoneNumber") String userName, @Query("password") String password,@Query("appNumber") String appNumber);

    /**
     * 用户详情
     * @return
     */
    @GET("culture/v1/user/info")
    Flowable<PadResultResponse<UserInfo>> getUserInfo(@Query("phoneNumber")String phoneNumber);

    /**
     * 用户列表
     * @param enterpriseId
     * @return
     */
    @GET("culture/v1/user/user/list")
    Flowable<PadResultResponse<PersonnelVo>> getUserList(@Query("enterpriseId")String enterpriseId);

    @GET("culture/v1/user/getRegisteVerifyCode/m/{mobile}")
    Flowable<PadResultResponse<CodeBean>> getRegisteVerifyCode(@Path("mobile") String mobile);

    @POST("culture/v1/user/changepassword")
    Flowable<PadResultResponse<Object>> changepassword(@Query("mobile")String mobile,@Query("verifyCode")String verifyCode,@Query("password")String password);

}
