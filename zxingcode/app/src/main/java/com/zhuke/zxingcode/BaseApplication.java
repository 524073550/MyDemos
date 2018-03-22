package com.zhuke.zxingcode;

import android.app.Application;


/**
 * Created by 15653 on 2018/3/20.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        ZXingLibrary.initDisplayOpinion(this);
    }
}
