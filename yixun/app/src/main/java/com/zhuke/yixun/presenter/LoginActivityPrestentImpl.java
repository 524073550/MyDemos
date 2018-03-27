package com.zhuke.yixun.presenter;

import android.text.TextUtils;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

/**
 * Created by 15653 on 2018/3/23.
 */

public class LoginActivityPrestentImpl implements LoginView.LoginViewActivityPrestenter {
    private LoginView.LoginViewActivity mLoginViewActivity;

    public LoginActivityPrestentImpl(LoginView.LoginViewActivity loginViewActivity) {
        this.mLoginViewActivity = loginViewActivity;
    }

    @Override
    public void login(String userName, String password) {
        if (TextUtils.isEmpty(userName)) {

            mLoginViewActivity.loginFailed("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mLoginViewActivity.loginFailed("请输入密码");
            return;
        }
        EMClient.getInstance().login(userName, password, new EMCallBack() {
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();//这两个方法最好是在splashactivity中初始化
                mLoginViewActivity.loginSuccse();
            }

            @Override
            public void onError(int i, String s) {
                mLoginViewActivity.loginFailed(s);

            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
}
