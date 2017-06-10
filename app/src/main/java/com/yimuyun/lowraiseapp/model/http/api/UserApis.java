package com.yimuyun.lowraiseapp.model.http.api;


import com.yimuyun.lowraiseapp.model.bean.UserBean;
import com.yimuyun.lowraiseapp.model.http.response.PadResultResponse;

import io.reactivex.Flowable;
import retrofit2.http.POST;
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
    Flowable<PadResultResponse<UserBean>> login(@Query("phoneNumber") String userName, @Query("password") String password);

}
