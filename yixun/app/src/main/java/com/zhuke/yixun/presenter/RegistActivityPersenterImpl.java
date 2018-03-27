package com.zhuke.yixun.presenter;

import android.text.TextUtils;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.zhuke.yixun.utils.ThreadUtils;

/**
 * Created by 15653 on 2018/3/23.
 */

public class RegistActivityPersenterImpl implements RegisterView.RegistActivityPersenter {
    private RegisterView.RegisterActivityView mRegisterActivityView;

    public RegistActivityPersenterImpl(RegisterView.RegisterActivityView registerActivityView) {
        this.mRegisterActivityView = registerActivityView;
    }

    @Override
    public void register(final String userName, final String pwd, final String pwdConfirm) {
        ThreadUtils.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (TextUtils.isEmpty(userName)){
                        mRegisterActivityView.registerFailed("请输入用户名");
                        return;
                    }
                    if (TextUtils.isEmpty(pwd)){
                        mRegisterActivityView.registerFailed("请输入密码");
                        return;
                    }
                    if (TextUtils.isEmpty(pwdConfirm)){
                        mRegisterActivityView.registerFailed("请确认密码");
                        return;
                    }
                    if (!pwd.equals(pwdConfirm)){
                        mRegisterActivityView.registerFailed("密码不一致");
                        return;
                    }

                    EMClient.getInstance().createAccount(userName, pwd);
                    ThreadUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mRegisterActivityView.registerSuccse();
                        }
                    });
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    ThreadUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mRegisterActivityView.registerFailed("注册失败");
                        }
                    });
                }
            }
        });
    }
}
