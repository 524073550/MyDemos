package com.zhuke.yixun.presenter;

/**
 * Created by 15653 on 2018/3/23.
 */

public class LoginView {
    public interface LoginViewActivity{
        void loginSuccse();
        void loginFailed(String failed);
    }

    public interface LoginViewActivityPrestenter{
        void login(String userName,String password);
    }
}
