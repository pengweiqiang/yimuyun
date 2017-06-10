package com.yimuyun.lowraiseapp.util;

import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.model.http.exception.ApiException;
import com.yimuyun.lowraiseapp.model.http.response.PadResultResponse;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pengweiqiang on 2016/8/3.
 */
public class RxUtil {

    /**
     * 统一线程处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


//    /**
//     * 统一返回结果处理
//     * @param <T>
//     * @return
//     */
//    public static <T> FlowableTransformer<WXHttpResponse<T>, T> handleWXResult() {   //compose判断结果
//        return new FlowableTransformer<WXHttpResponse<T>, T>() {
//            @Override
//            public Flowable<T> apply(Flowable<WXHttpResponse<T>> httpResponseFlowable) {
//                return httpResponseFlowable.flatMap(new Function<WXHttpResponse<T>, Flowable<T>>() {
//                    @Override
//                    public Flowable<T> apply(WXHttpResponse<T> tWXHttpResponse) {
//                        if(tWXHttpResponse.getCode() == 200) {
//                            return createData(tWXHttpResponse.getNewslist());
//                        } else {
//                            return Flowable.error(new ApiException("服务器返回error"));
//                        }
//                    }
//                });
//            }
//        };
//    }

    /**
     * 统一返回结果处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<PadResultResponse<T>, T> handleMyResult() {   //compose判断结果
        return new FlowableTransformer<PadResultResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<PadResultResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<PadResultResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(PadResultResponse<T> tPadResultResponse) {
                        if(tPadResultResponse.getHead().getRet()==Constants.CODE_SUCCESS) {
                            return createData(tPadResultResponse.getBody());
                        } else {
                            return Flowable.error(new ApiException(tPadResultResponse.getHead().getMsg()));
                        }
                    }
                });
            }
        };
    }

//    /**
//     * 统一返回结果处理
//     * @param <T>
//     * @return
//     */
//    public static <T> FlowableTransformer<GoldHttpResponse<T>, T> handleGoldResult() {   //compose判断结果
//        return new FlowableTransformer<GoldHttpResponse<T>, T>() {
//            @Override
//            public Flowable<T> apply(Flowable<GoldHttpResponse<T>> httpResponseFlowable) {
//                return httpResponseFlowable.flatMap(new Function<GoldHttpResponse<T>, Flowable<T>>() {
//                    @Override
//                    public Flowable<T> apply(GoldHttpResponse<T> tGoldHttpResponse) {
//                        if(tGoldHttpResponse.getResults() != null) {
//                            return createData(tGoldHttpResponse.getResults());
//                        } else {
//                            return Flowable.error(new ApiException("服务器返回error"));
//                        }
//                    }
//                });
//            }
//        };
//    }

    /**
     * 生成Flowable
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }
}
