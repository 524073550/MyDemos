package com.zhuke.rxutils;

import android.app.Application;

import com.vondear.rxtools.RxTool;

/**
 * Created by 15653 on 2018/3/21.
 */

public class RxApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RxTool.init(this);
    }
}
