package com.zhuke.yixun.presenter;

import android.text.TextUtils;

import com.hyphenate.chat.EMClient;

/**
 * Created by 15653 on 2018/3/23.
 */

public class RegistActivityPersenterImpl implements RegisterView.RegistActivityPersenter {
    private RegisterView.RegisterActivityView mRegisterActivityView;

    public RegistActivityPersenterImpl(RegisterView.RegisterActivityView registerActivityView) {
        this.mRegisterActivityView = registerActivityView;
    }

    @Override
    public void register(String userName, String pwd, String pwdConfirm) {
            try{
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

                EMClient.getInstance().createAccount(userName, pwd);//同步方法
                mRegisterActivityView.registerSuccse();
            }catch (Exception e){
                mRegisterActivityView.registerFailed("");
                e.printStackTrace();
            }
    }
}
