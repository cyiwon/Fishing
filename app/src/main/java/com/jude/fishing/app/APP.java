package com.jude.fishing.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.jude.beam.Beam;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.fishing.R;
import com.jude.fishing.config.Dir;
import com.jude.utils.JFileManager;
import com.jude.utils.JUtils;
import com.umeng.analytics.MobclickAgent;

import io.rong.imkit.RongIM;


/**
 * Created by Mr.Jude on 2015/1/27.
 */
public class APP extends Application {
    private static APP instance = null;
    public static APP getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        /* OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIM 的进程和 Push 进程执行了 init。
         * xxx.xxx.xxx 是您的主进程或者使用了 RongIM 的其他进程 */
        if("com.jude.fishing".equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

            /* IMKit SDK调用第一步 初始化 */
            RongIM.init(this);

            /* 必须在使用 RongIM 的进程注册回调、注册自定义消息等 */
            if ("com.jude.fishing".equals(getCurProcessName(getApplicationContext()))) {
                instance = this;
                Fresco.initialize(this);
                JUtils.initialize(this);
                JUtils.setDebug(true, "Fishing");
                Fresco.initialize(this);
                JFileManager.getInstance().init(this, Dir.values());
                MobclickAgent.updateOnlineConfig(this);

                Beam.init(this);
                Beam.registerActivityLifetCyclerDelegate(ActivityDelegate.class);
                ListConfig.setDefaultListConfig(new ListConfig().
                        setRefreshAble(true).
                        setContainerLayoutRes(R.layout.activity_recyclerview));
            }
        }
    }


    /* 一个获得当前进程的名字的方法 */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}
