package com.yimuyun.lowraiseapp.base;

/**
 * Created by pengwq on 2017/04/27.
 * View基类
 */
public interface BaseView {

    //使用snackBar展示错误信息
    void showErrorMsg(String msg);
    //使用toast展示错误信息
    void showErrorMsgToast(String msg);
    //是否使用夜晚模式
    void useNightMode(boolean isNight);

    //=======  State  =======
    //网络加载失败
    void stateError();
    //加载中
    void stateLoading();
    //加载中
    void stateLoading(String msg);
    //取消加载
    void stateMain();
    //是否展示view_main,并且展示view_error
    void showViewMain(boolean isShowViewMain, String errorMsg);
    //提示框
    void showMsgDialog(String title, String msg);
    //开启登陆
    void startLoginActivity();
    //重新加载，刷新
    void reload();
    //取消进度条
    void cancelDialog();
}
