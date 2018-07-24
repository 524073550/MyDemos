package business.com.businessapp.account;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import business.com.businessapp.R;
import business.com.businessapp.base.BaseActivity;
import business.com.businessapp.base.mvp.IBaseView;

/**
 * Created by decheng.yang on 2018/2/22.
 */

public class WelcomeActivity extends BaseActivity implements IBaseView {
    private Handler handler;
    private Runnable jumpRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

    }

    @Override
    protected void onStart() {
        super.onStart();
        intentUi(2000);
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setData() {

    }


    private void intentUi(long delayMillis) {
        jumpRunnable = new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        };
        handler = new Handler();
        handler.postDelayed(jumpRunnable, delayMillis);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void showLoading(String msg, int progress) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void showErrorMsg(String msg, String content) {

    }

    @Override
    public void close() {

    }

    @Override
    public boolean isActive() {
        return false;
    }
}
