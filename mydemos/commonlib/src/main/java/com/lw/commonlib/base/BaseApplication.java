package com.lw.commonlib.base;

import android.app.Application;

/**
 * BaseApplication
 * Created by luwei on 2016/7/10.
 */
public class BaseApplication extends Application {
    private static BaseApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static BaseApplication getInstance() {
        return instance;
    }
}
