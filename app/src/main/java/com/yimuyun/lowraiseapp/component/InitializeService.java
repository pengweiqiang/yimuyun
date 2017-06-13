package com.yimuyun.lowraiseapp.component;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;
import com.yimuyun.lowraiseapp.app.Constants;
import com.yimuyun.lowraiseapp.util.SystemUtil;

import static com.yimuyun.lowraiseapp.util.LogUtil.isDebug;


/**
 * Created by pengweiqiang on 2017/2/12.
 */

public class InitializeService extends IntentService {

    private static final String ACTION_INIT = "initApplication";

    public InitializeService() {
        super("InitializeService");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT.equals(action)) {
                initApplication();
            }
        }
    }

    private void initApplication() {
        //初始化日志
        Logger.init(getPackageName()).hideThreadInfo();

        //初始化错误收集
        CrashHandler.init(new CrashHandler(getApplicationContext()));
        initBugly();



//        //初始化tbs x5 webview
//        QbSdk.allowThirdPartyAppDownload(true);
//        QbSdk.initX5Environment(getApplicationContext(), QbSdk.WebviewInitType.FIRSTUSE_AND_PRELOAD, new QbSdk.PreInitCallback() {
//            @Override
//            public void onCoreInitFinished() {
//            }
//
//            @Override
//            public void onViewInitFinished(boolean b) {
//            }
//        });
    }

    private void initBugly() {
        Context context = getApplicationContext();
        String packageName = context.getPackageName();
        //获取当前进程名
        String processName = SystemUtil.getProcessName(android.os.Process.myPid());
        //设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        //初始化Bugly
//        CrashReport.initCrashReport(context, Constants.BUGLY_ID, isDebug, strategy);
        Bugly.init(context, Constants.BUGLY_ID,isDebug,strategy);
    }
}
