package com.zhuke.yixun.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.zhuke.comlibrary.base.BaseActivity;
import com.zhuke.comlibrary.utils.MyToast;
import com.zhuke.yixun.R;
import com.zhuke.yixun.presenter.RegistActivityPersenterImpl;
import com.zhuke.yixun.presenter.RegisterView;

/**
 * Created by 15653 on 2018/3/22.
 */

public class RegisterActivity extends BaseActivity implements RegisterView.RegisterActivityView {

    private EditText mUser_name;
    private EditText mPassword;
    private EditText mConfirm_password;
    private Button mRegist;
    private RegisterView.RegistActivityPersenter mRegistActivityPersenter;

    @Override
    protected View addContent(LayoutInflater layoutInflater, FrameLayout container) {

        return layoutInflater.inflate(R.layout.activity_register,container,true);
    }

    @Override
    protected void initContent(View view) {
        mUser_name = (EditText) view.findViewById(R.id.user_name);
        mPassword = (EditText) view.findViewById(R.id.password);
        mConfirm_password = (EditText) view.findViewById(R.id.confirm_password);
        mRegist = (Button) view.findViewById(R.id.register);
        mRegist.setOnClickListener(this);
    }

    @Override
    protected void initTitle() {
        setTitle("注册");
        setLeftIV(true,this);
        mRegistActivityPersenter = new RegistActivityPersenterImpl(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.register:
                String user_name = mUser_name.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String confirm = mConfirm_password.getText().toString().trim();
                mRegistActivityPersenter.register(user_name,password,confirm);
                break;
        }

    }

    @Override
    public void registerSuccse() {
        finish();
    }

    @Override
    public void registerFailed(String msg) {
        MyToast.showThread(this,msg);
    }
}
