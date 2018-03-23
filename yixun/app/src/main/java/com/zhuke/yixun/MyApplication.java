package com.zhuke.yixun;

import android.app.Application;
import android.content.Context;

import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.zhuke.comlibrary.BaseApplication;

/**
 * Created by 15653 on 2018/3/22.
 */

public class MyApplication extends BaseApplication {

    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true);
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true);
        //是否需要自动登录
        options.setAutoLogin(false);
        EaseUI.getInstance().init(this, options);
        EMClient.getInstance().setDebugMode(true);

    }


    public Context getContext() {
        return mContext;
    }
}
