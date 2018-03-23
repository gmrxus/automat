package com.zzrh.automat;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/2.
 */

public class App extends Application {
    public static Context sContext = null;
    private static List<Activity> activities = null;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        Logger.addLogAdapter(new AndroidLogAdapter());
        CrashReport.initCrashReport(getApplicationContext(), "00add05487", false);
    }

    public static void addActivity(Activity activity) {
        if (activities == null) {
            activities = new ArrayList<>();
        }
        activities.add(activity);
    }

    public static void removeAll() {
        for (int i = 0; i < activities.size(); i++) {
            activities.remove(i);
        }
        activities.clear();
    }

    public static void remove(Activity activity) {
        activities.remove(activity);
    }




}
