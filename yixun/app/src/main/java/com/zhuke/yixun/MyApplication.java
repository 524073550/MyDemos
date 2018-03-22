package com.zhuke.yixun;

import android.app.Application;
import android.content.Context;

import com.tencent.imsdk.TIMGroupReceiveMessageOpt;
import com.tencent.imsdk.TIMLogListener;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMOfflinePushListener;
import com.tencent.imsdk.TIMOfflinePushNotification;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.zhuke.yixun.utils.Foreground;

import static com.tencent.qalsdk.service.QalService.context;

/**
 * Created by 15653 on 2018/3/22.
 */

public class MyApplication extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
        Foreground.init(this);
        context = getApplicationContext();
        if(MsfSdkUtils.isMainProcess(this)) {
            TIMManager.getInstance().setOfflinePushListener(new TIMOfflinePushListener() {
                @Override
                public void handleNotification(TIMOfflinePushNotification notification) {
                    if (notification.getGroupReceiveMsgOpt() == TIMGroupReceiveMessageOpt.ReceiveAndNotify){
                        //消息被设置为需要提醒
                        notification.doNotify(getApplicationContext(), R.mipmap.ic_launcher);
                    }
                }
            });
        }
    }

    public static Context getContext() {
        return context;
    }


}
