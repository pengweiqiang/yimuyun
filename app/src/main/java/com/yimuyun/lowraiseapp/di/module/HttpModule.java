package com.yimuyun.lowraiseapp.di.module;


import android.util.Log;

import com.yimuyun.lowraiseapp.BuildConfig;
import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.di.qualifier.BaseUrl;
import com.yimuyun.lowraiseapp.model.DataManager;
import com.yimuyun.lowraiseapp.model.bean.UserBean;
import com.yimuyun.lowraiseapp.model.http.api.FeedApis;
import com.yimuyun.lowraiseapp.model.http.api.UserApis;
import com.yimuyun.lowraiseapp.util.LogUtil;
import com.yimuyun.lowraiseapp.util.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pengweiqiang on 2017/5/7.
 */

@Module
public class HttpModule {

    @Inject
    protected DataManager dataManager;

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

/*    @Singleton
    @Provides
    @UserLoginUrl
    Retrofit.Builder provideLoginRetrofitBuilder() {
        return new Retrofit.Builder();
    }*/


    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    @BaseUrl
    Retrofit provideGoldRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        Log.d("URL", "provideGoldRetrofit");
        return createRetrofit(builder, client, Constants.HOST_URL);
    }

//    @Singleton
//    @Provides
//    @UserLoginUrl
//    Retrofit provideUploadRetrofit(Retrofit.Builder builder, OkHttpClient client) {
//        Log.d("URL", "provideUploadRetrofit");
//        return createRetrofit(builder, client, Constants.HOST_URL);
//    }


/*    @Singleton
    @Provides
    @UserLoginUrl
    OkHttpClient provideLoginClient(@UserLoginUrl OkHttpClient.Builder builder) {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        File cacheFile = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!SystemUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (SystemUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        //TODO 设置统一的请求头部参数
        Interceptor apikey = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Log.d("URL", request.url().toString());
                HttpUrl.Builder builder = request.url().newBuilder()
                        .scheme(request.url().scheme())
                        .host(request.url().host())
                        .addQueryParameter("ipAddress", "111")
                        .addQueryParameter("macAddress", "11")
                        .addQueryParameter("macAddress", "11")
                        .addQueryParameter("appVersionName", "11");
                Request newRequest = request.newBuilder()
                        .method(request.method(), request.body())
                        .url(builder.build())
                        .build();
                Log.d("URL", newRequest.url().toString());
                return chain.proceed(newRequest);
            }
        };
//        设置统一的请求头部参数
        builder.addInterceptor(apikey);
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }*/


    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder) {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        File cacheFile = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!SystemUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (SystemUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时, 不缓存, 最大保存时长为0
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
                return response;
            }
        };
        //TODO 设置统一的请求头部参数
        Interceptor apikey = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                long startTime = System.currentTimeMillis();
                Request request = chain.request();
                UserBean userBean = App.getInstance().getUserBeanInstance();
                String token = "";
                String uid = "";
                if(userBean!=null){
                    token = userBean.getToken();
                    uid = String.valueOf(userBean.getUid());
                }

                HttpUrl.Builder builder = request.url().newBuilder()
                        .scheme(request.url().scheme())
                        .addQueryParameter("token",token)
                        .addQueryParameter("uid",uid)
                        .host(request.url().host());
                Request newRequest = request.newBuilder()
                        .method(request.method(), request.body())
                        .url(builder.build())
                        .build();

                Response response = chain.proceed(newRequest);
                MediaType mediaType = response.body().contentType();
                String content = response.body().string();
                LogUtil.d("------------------Start-----------------");
                LogUtil.d("URL:"+newRequest.url().toString());
                LogUtil.d("request: "+newRequest.toString());

                String method = request.method();
                if("POST".equals(method)){
                    StringBuilder sb = new StringBuilder();
                    if (request.body() instanceof FormBody) {
                        FormBody body = (FormBody) request.body();
                        for (int i = 0; i < body.size(); i++) {
                            sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                        }
                        sb.delete(sb.length() - 1, sb.length());
                        LogUtil.d("| RequestParams:{"+sb.toString()+"}");
                    }
                }
                long endTime = System.currentTimeMillis();
                long duration=endTime-startTime;
                LogUtil.d("response: "+content);
                LogUtil.d("-------------End：costs="+duration+"毫秒------------");

                return response.newBuilder().body(ResponseBody.create(mediaType,content)).build();
            }
        };
//        设置统一的请求头部参数
        builder.addInterceptor(apikey);
        //设置缓存
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.cache(cache);
        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }


    @Singleton
    @Provides
    UserApis provideGoldService(@BaseUrl Retrofit retrofit) {
        return retrofit.create(UserApis.class);
    }

    @Singleton
    @Provides
    FeedApis provideFeedService(@BaseUrl Retrofit retrofit) {
        return retrofit.create(FeedApis.class);
    }




    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
