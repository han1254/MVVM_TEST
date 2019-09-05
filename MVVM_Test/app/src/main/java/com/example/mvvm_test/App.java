package com.example.mvvm_test;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.example.android.utils.AppRuntimeInit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class App extends Application {


    private static App sInstance;

    private static Context mContext;

    private String cachePath;

    private static final long EXIT_TIME = 2000;

    private static long sTimeMillis = 0;

    private static List<Activity> sActivityList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;
        cachePath = Objects.requireNonNull(getExternalCacheDir()).getAbsolutePath();
        registerActivityLifecycleCallbacks(getActivityLifecycleCallbacks());
        //获取context
        mContext = getApplicationContext();

    }

    public ActivityLifecycleCallbacks getActivityLifecycleCallbacks() {
        return new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                sActivityList.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                sActivityList.remove(activity);
            }
        };
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        AppRuntimeInit.onApplicationAttachBaseContext(this, BuildConfig.DEBUG);
    }

    //创建一个静态的方法，以便获取context对象
    public static Context getContext(){
        return mContext;
    }


    public static App getInstance() {
        return sInstance;
    }

}
