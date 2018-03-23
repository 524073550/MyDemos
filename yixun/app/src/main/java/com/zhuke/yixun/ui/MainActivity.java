package com.zhuke.yixun.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zhuke.comlibrary.base.BaseActivity;
import com.zhuke.yixun.R;



public class MainActivity extends BaseActivity {



    @Override
    protected View addContent(LayoutInflater layoutInflater, FrameLayout container) {
        return layoutInflater.inflate(R.layout.activity_main, container, true);
    }

    @Override
    protected void initContent(View view) {

    }

    @Override
    protected void initTitle() {

    }


    public void onViewClicked(View view) {
    }
}
