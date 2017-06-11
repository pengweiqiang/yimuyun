package com.yimuyun.lowraiseapp.di.component;

import android.app.Activity;

import com.yimuyun.lowraiseapp.di.module.ActivityModule;
import com.yimuyun.lowraiseapp.di.scope.ActivityScope;
import com.yimuyun.lowraiseapp.ui.LoginActivity;
import com.yimuyun.lowraiseapp.ui.MainActivity;
import com.yimuyun.lowraiseapp.ui.WelcomeActivity;
import com.yimuyun.lowraiseapp.ui.diagnosis.DiagnosisManageActivity;
import com.yimuyun.lowraiseapp.ui.disinfect.DisinfectManageActivity;
import com.yimuyun.lowraiseapp.ui.feed.FeedListActivity;
import com.yimuyun.lowraiseapp.ui.feed.FeedManageActivity;
import com.yimuyun.lowraiseapp.ui.fertilization.FertilizationManageActivity;
import com.yimuyun.lowraiseapp.ui.immune.ImmuneManageActivity;
import com.yimuyun.lowraiseapp.ui.neweartag.NewEarTagManageActivity;
import com.yimuyun.lowraiseapp.ui.weight.WeightManageActivity;

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

    void inject(FeedManageActivity feedManageActivity);

    void inject(FeedListActivity feedListActivity);

    void inject(WeightManageActivity weightManageActivity);

    void inject(ImmuneManageActivity immuneManageActivity);

    void inject(DisinfectManageActivity disinfectManageActivity);

    void inject(DiagnosisManageActivity diagnosisManageActivity);

    void inject(NewEarTagManageActivity diagnosisManageActivity);

    void inject(FertilizationManageActivity fertilizationManageActivity);

//
//    void inject(ThemeActivity themeActivity);
//
//    void inject(SectionActivity sectionActivity);
//
//    void inject(RepliesActivity repliesActivity);
//
//    void inject(NodeListActivity nodeListActivity);
}
