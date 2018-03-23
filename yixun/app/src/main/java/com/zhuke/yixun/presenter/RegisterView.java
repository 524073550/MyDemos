package com.zhuke.yixun.presenter;

/**
 * Created by 15653 on 2018/3/23.
 */

public class RegisterView {
    public interface RegisterActivityView{
        void registerSuccse();
        void registerFailed(String msg);
    }
    public interface RegistActivityPersenter{
        void register(String userName, String pwd, String pwdConfirm);
    }
}
