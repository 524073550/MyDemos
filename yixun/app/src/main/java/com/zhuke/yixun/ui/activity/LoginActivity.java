package com.zhuke.yixun.ui.activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zhuke.comlibrary.base.BaseActivity;
import com.zhuke.comlibrary.utils.MyToast;
import com.zhuke.yixun.R;
import com.zhuke.yixun.presenter.LoginActivityPrestentImpl;
import com.zhuke.yixun.presenter.LoginView;

/**
 * Created by 15653 on 2018/3/22.
 */

public class LoginActivity extends BaseActivity implements LoginView.LoginViewActivity {


    private EditText mName;
    private EditText mPassword;
    private TextView mGorgetTv;
    private TextView mRegistTv;
    private Button mLoginBt;
    private LoginActivityPrestentImpl mLoginActivityPrestent;

    @Override
    protected View addContent(LayoutInflater layoutInflater, FrameLayout container) {

        return layoutInflater.inflate(R.layout.activity_login, container, true);
    }

    @Override
    protected void initContent(View view) {
        mName = (EditText) view.findViewById(R.id.login_et_ph);
        mPassword = (EditText) view.findViewById(R.id.login_et_pwd);
        mGorgetTv = (TextView) view.findViewById(R.id.login_tv_forget);
        mRegistTv = (TextView) view.findViewById(R.id.login_tv_regist);
        mLoginBt = (Button) view.findViewById(R.id.login_bt_login);
        mGorgetTv.setOnClickListener(this);
        mRegistTv.setOnClickListener(this);
        mLoginBt.setOnClickListener(this);
    }

    @Override
    protected void initTitle() {
        setRLTitleVisibility(false);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.login_tv_regist:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_tv_forget:
                break;
            case R.id.login_bt_login:
                login();
                break;
        }
    }


    private void login() {
        String name = mName.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        mLoginActivityPrestent = new LoginActivityPrestentImpl(this);
        mLoginActivityPrestent.login(name,password);
    }

    @Override
    public void onNetChange(boolean netWorkState) {
       if (netWorkState){
           showToast("网络可用");
       }else {
           showToast("网络不可用");
       }
    }

    @Override
    public void loginSuccse() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void loginFailed(String failed) {
        MyToast.showThread(this,failed);
    }
}
