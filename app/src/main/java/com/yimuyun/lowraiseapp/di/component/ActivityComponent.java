package com.yimuyun.lowraiseapp.di.component;

import android.app.Activity;

import com.yimuyun.lowraiseapp.di.module.ActivityModule;
import com.yimuyun.lowraiseapp.di.scope.ActivityScope;
import com.yimuyun.lowraiseapp.ui.LoginActivity;
import com.yimuyun.lowraiseapp.ui.MainActivity;
import com.yimuyun.lowraiseapp.ui.WelcomeActivity;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(WelcomeActivity welcomeActivity);

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

//
//    void inject(ThemeActivity themeActivity);
//
//    void inject(SectionActivity sectionActivity);
//
//    void inject(RepliesActivity repliesActivity);
//
//    void inject(NodeListActivity nodeListActivity);
}
