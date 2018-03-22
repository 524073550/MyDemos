package com.zhuke.yixun;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tencent.imsdk.TIMLogListener;
import com.tencent.imsdk.TIMManager;
import com.zhuke.comlibrary.base.BaseActivity;

/**
 * Created by 15653 on 2018/3/21.
 */

public class LoginActivity extends BaseActivity {
    private EditText mUser_num;
    private EditText mUser_pass;
    private TextView mRegistTV;
    private TextView mForgetTV;
    private Button mLoginBT;

    @Override
    protected View addContent(LayoutInflater layoutInflater, FrameLayout container) {

        return layoutInflater.inflate(R.layout.activity_login,container,true);
    }

    @Override
    protected void initContent(View view) {
        mUser_num = (EditText) view.findViewById(R.id.login_et_ph);
        mUser_pass = (EditText) view.findViewById(R.id.login_et_pwd);
        mRegistTV = (TextView) view.findViewById(R.id.login_tv_regist);
        mForgetTV = (TextView) view.findViewById(R.id.login_tv_forget);
        mLoginBT = (Button) view.findViewById(R.id.login_bt_login);
    }

    @Override
    protected void initTitle() {
        setRLTitleVisibility(false);

    }
}
