package com.yimuyun.lowraiseapp.base;

/**
 * Created by pengwq on 2017/04/27.
 * Presenter基类
 */
public interface BasePresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();
}
