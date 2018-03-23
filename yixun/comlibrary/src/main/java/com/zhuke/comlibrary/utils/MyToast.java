package com.zhuke.comlibrary.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by Apple on 2016/9/26.
 * 吐司的工具类
 */
public final class MyToast {

    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showThread(final Context context, final String msg) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        //放在UI线程弹Toast
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }
}
