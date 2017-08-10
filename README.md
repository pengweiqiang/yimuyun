# 养殖畜牧端-APP

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A pure reading App based on Material Design + MVP + RxJava2 + Retrofit + Dagger2 + Realm + Glide

极客日报，一款纯粹的阅读App，基于Material Design + MVP + RxJava2 + Retrofit + Dagger2 + Realm + Glide

基本涵盖了当前Android端开发最常用的主流框架，阅读内容主要面向程序员/极客，目前包括:

* IDE提示缺少Dagger开头的Class直接编译即可，会由Dagger2自动生成


## Points

* 使用RxJava配合Retrofit2做网络请求
* 使用RxUtil对线程操作和网络请求结果处理做了封装
* 使用RxPresenter对订阅的生命周期做管理
* 使用RxBus来方便组件间的通信
* 使用RxJava其他操作符来做延时、轮询、转化、筛选等操作
* 使用okhttp3对网络返回内容做缓存，还有日志、超时重连、头部消息的配置
* 使用Material Design控件和动画
* 使用MVP架构整个项目，对应于model、ui、presenter三个包
* 使用Dagger2将M层注入P层，将P层注入V层，无需new，直接调用对象
* 使用Realm做阅读记录和收藏记录的增、删、查、改
* 使用Glide做图片的处理和加载
* 使用Fragmentation简化Fragment的操作和懒加载
* 使用RecyclerView实现下拉刷新、上拉加载、侧滑删除、长按拖曳
* 使用x5WebView做阅览页，比原生WebView体验更佳
* 使用SVG及其动画实现progressbar的效果
* 使用RxPermissions做6.0+动态权限适配
* RFID读取耳标设备ID
* 使用Material-dialogs 实现dialog



### LIB:
#### UI
* [MaterialCalendarView](https://github.com/prolificinteractive/material-calendarview)
* [MaterialSearchView](https://github.com/MiguelCatalan/MaterialSearchView)
* [PhotoView](https://github.com/chrisbanes/PhotoView)
* [multiline-collapsingtoolbar](https://github.com/opacapp/multiline-collapsingtoolbar)
* [glide-transformations](https://github.com/wasabeef/glide-transformations)
* [html-textview](https://github.com/SufficientlySecure/html-textview)

#### RX

* [RxJava](https://github.com/ReactiveX/RxJava)
* [RxAndroid](https://github.com/ReactiveX/RxAndroid)
* [RxPermissions](https://github.com/tbruyelle/RxPermissions)

#### NETWORK

* [Retrofit](https://github.com/square/retrofit)
* [OkHttp](https://github.com/square/okhttp)
* [Glide](https://github.com/bumptech/glide)
* [Gson](https://github.com/google/gson)
* [Jsoup](https://github.com/jhy/jsoup)

#### DI

* [Dagger2](https://github.com/google/dagger)
* [ButterKnife](https://github.com/JakeWharton/butterknife)

#### FRAGMENT

* [Fragmentation](https://github.com/YoKeyword/Fragmentation)

#### LOG

* [Logger](https://github.com/orhanobut/logger)

#### DB

* [Realm](https://github.com/realm/realm-java)

#### CANARY

* [BlockCanary](https://github.com/markzhai/AndroidPerformanceMonitor)
* [LeakCanary](https://github.com/square/leakcanary)

#### Material-dialogs

*[Material-dialogs](https://github.com/afollestad/material-dialogs)
