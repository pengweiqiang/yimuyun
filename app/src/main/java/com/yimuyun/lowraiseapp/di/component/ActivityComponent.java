package com.yimuyun.lowraiseapp.di.component;

import android.app.Activity;

import com.yimuyun.lowraiseapp.di.module.ActivityModule;
import com.yimuyun.lowraiseapp.di.scope.ActivityScope;
import com.yimuyun.lowraiseapp.ui.LoginActivity;
import com.yimuyun.lowraiseapp.ui.MainActivity;
import com.yimuyun.lowraiseapp.ui.UserListActivity;
import com.yimuyun.lowraiseapp.ui.WelcomeActivity;
import com.yimuyun.lowraiseapp.ui.diagnosis.AddDiagnosisRecordActivity;
import com.yimuyun.lowraiseapp.ui.diagnosis.DiagnosisManageActivity;
import com.yimuyun.lowraiseapp.ui.diagnosis.DiagnosisResultListActivity;
import com.yimuyun.lowraiseapp.ui.diagnosis.DiagnosisTreatmentPlanListActivity;
import com.yimuyun.lowraiseapp.ui.diagnosis.DrugListActivity;
import com.yimuyun.lowraiseapp.ui.disinfect.DisifectWaysListActivity;
import com.yimuyun.lowraiseapp.ui.disinfect.DisinfectManageActivity;
import com.yimuyun.lowraiseapp.ui.environmentmonitor.EnvironmentMonitorActivity;
import com.yimuyun.lowraiseapp.ui.feed.FeedListActivity;
import com.yimuyun.lowraiseapp.ui.feed.FeedManageActivity;
import com.yimuyun.lowraiseapp.ui.fertilization.FertilizationManageActivity;
import com.yimuyun.lowraiseapp.ui.immune.ImmuneManageActivity;
import com.yimuyun.lowraiseapp.ui.innocuitydeal.InnocuityDealActivity;
import com.yimuyun.lowraiseapp.ui.neweartag.NewEarTagManageActivity;
import com.yimuyun.lowraiseapp.ui.offsale.OffSaleActivity;
import com.yimuyun.lowraiseapp.ui.purchase.PurchaseActivity;
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

    void inject(EnvironmentMonitorActivity environmentMonitorActivity);

    void inject(InnocuityDealActivity innocuityDealActivity);

    void inject(OffSaleActivity offSaleActivity);

    void inject(PurchaseActivity purchaseActivity);

    void inject(UserListActivity userListActivity);

    void inject(AddDiagnosisRecordActivity addDiagnosisRecordActivity);

    void inject(DiagnosisResultListActivity diagnosisResultListActivity);

    void inject(DiagnosisTreatmentPlanListActivity diagnosisTreatmentPlanListActivity);

    void inject(DrugListActivity drugListActivity);

    void inject(DisifectWaysListActivity disifectWaysListActivity);

}
