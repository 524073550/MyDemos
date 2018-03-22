package com.lw.commonlib.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.lw.commonlib.R;
import com.lw.commonlib.base.utils.ActivityManager;
import com.lw.commonlib.base.utils.volly.RequestManager;


/**
 * BaseActivity
 * Created by luwei on 2016/7/10.
 */
public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {
    protected RequestManager mRequestManager;
    protected Object mVolleyTag;
    protected int PAGE_SIZE = 10;
    protected int mPageIndex = 1;
    protected FrameLayout layout_white_loading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().pushActivity(this);
        mRequestManager = RequestManager.getInstance(getApplicationContext());
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initData();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    protected abstract void initData();

    protected Object getVolleyTag() {
        if (mVolleyTag == null) {
            mVolleyTag = String.valueOf(hashCode());
        }
        return mVolleyTag;
    }

    public void addRequestQueue(Request request) {
        mRequestManager.addToRequestQueue(request, getVolleyTag());
    }

    protected void cancelRequestQueue() {
        mRequestManager.cancel(getVolleyTag());
    }

    protected void addRequestQueue(Request request, Object tag) {
        mRequestManager.addToRequestQueue(request, tag);
    }

    protected void delRequestQueue(Object tag) {
        mRequestManager.cancel(tag);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cancelRequestQueue();
        ActivityManager.getInstance().popActivity(this);
    }


    @Override
    public void onClick(View mView) {

    }

    public void showToast(String text) {
        Toast.makeText(BaseApplication.getInstance(), text, Toast.LENGTH_SHORT).show();
    }

    protected void enableLeftIV() {
        enableLeftIV(null);
    }

    protected void enableLeftIV(View.OnClickListener listener) {
        View leftIV = findViewById(R.id.title_left_iv);
        leftIV.setVisibility(View.VISIBLE);
        if (listener == null) {
            leftIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }else{
            leftIV.setOnClickListener(listener);
        }
    }

    protected Button enableRightBtn(String text, View.OnClickListener listener) {
        Button btn = (Button) findViewById(R.id.title_right_btn);
        btn.setText(text);
        btn.setVisibility(View.VISIBLE);
        btn.setOnClickListener(listener);
        return btn;
    }

    public void setTitleText(String text) {
        TextView title = (TextView) findViewById(R.id.title_name_tv);
        title.setVisibility(View.VISIBLE);
        title.setText(text);
    }

    protected void enableRightImage(int resId, View.OnClickListener listener) {
        ImageView iv = (ImageView) findViewById(R.id.title_right_iv);
        iv.setImageResource(resId);
        iv.setOnClickListener(listener);
        iv.setVisibility(View.VISIBLE);
    }

    protected void enableRightImage2(int resId, View.OnClickListener listener) {
        ImageView btn = (ImageView) findViewById(R.id.title_right_iv2);
        btn.setImageResource(resId);
        btn.setOnClickListener(listener);
        btn.setVisibility(View.VISIBLE);
    }

    protected ProgressDialog progressBar;

    protected void showProgressBar(String message) {
        try {
            progressBar = new ProgressDialog(this);
            progressBar.setMessage(message);
            progressBar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void dismissProgressBar() {
        try {
            if (progressBar != null && progressBar.isShowing()) {
                progressBar.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void showWhitLoading() {
        if (layout_white_loading == null) {
            layout_white_loading = (FrameLayout) findViewById(R.id.layout_white_loading);
        }
        layout_white_loading.setVisibility(View.VISIBLE);
    }

    protected void hideWhitLoading() {
        if (layout_white_loading != null) {
            layout_white_loading.setVisibility(View.GONE);
        }
    }

}
