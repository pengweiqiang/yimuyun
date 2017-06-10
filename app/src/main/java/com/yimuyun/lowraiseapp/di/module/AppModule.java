package com.yimuyun.lowraiseapp.di.module;


import com.yimuyun.lowraiseapp.app.App;
import com.yimuyun.lowraiseapp.model.DataManager;
import com.yimuyun.lowraiseapp.model.db.DBHelper;
import com.yimuyun.lowraiseapp.model.db.RealmHelper;
import com.yimuyun.lowraiseapp.model.http.HttpHelper;
import com.yimuyun.lowraiseapp.model.http.RetrofitHelper;
import com.yimuyun.lowraiseapp.model.prefs.ImplPreferencesHelper;
import com.yimuyun.lowraiseapp.model.prefs.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pengweiqiang on 17/5/7.
 */

@Module
public class AppModule {
    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }


    @Provides
    @Singleton
    DBHelper provideDBHelper(RealmHelper realmHelper) {
        return realmHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(ImplPreferencesHelper implPreferencesHelper) {
        return implPreferencesHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, DBHelper DBHelper, PreferencesHelper preferencesHelper) {
        return new DataManager(httpHelper, DBHelper, preferencesHelper);
    }
}
