package com.yimuyun.lowraiseapp.di.component;

import android.app.Activity;

import com.yimuyun.lowraiseapp.di.module.FragmentModule;
import com.yimuyun.lowraiseapp.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();


//
//    void inject(CommentFragment longCommentFragment);
//
//    void inject(TechFragment techFragment);
//
//    void inject(GirlFragment girlFragment);
//
//    void inject(LikeFragment likeFragment);
//
//    void inject(WechatMainFragment wechatMainFragment);
//
//    void inject(SettingFragment settingFragment);
//
//    void inject(GoldMainFragment goldMainFragment);
//
//    void inject(GoldPagerFragment goldPagerFragment);
//
//    void inject(VtexPagerFragment vtexPagerFragment);
}
