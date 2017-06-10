package com.yimuyun.lowraiseapp.di.component;


import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.di.module.AppModule;
import com.yimuyun.lowraiseapp.di.module.HttpModule;
import com.yimuyun.lowraiseapp.model.DataManager;
import com.yimuyun.lowraiseapp.model.db.RealmHelper;
import com.yimuyun.lowraiseapp.model.http.RetrofitHelper;
import com.yimuyun.lowraiseapp.model.prefs.ImplPreferencesHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    App getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类

    RealmHelper realmHelper();    //提供数据库帮助类

    ImplPreferencesHelper preferencesHelper(); //提供sp帮助类
}
