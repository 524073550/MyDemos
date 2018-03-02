package com.zhuke.retrofitrxjavademo.net;

import com.zhuke.retrofitrxjavademo.UrlContent;
import com.zhuke.retrofitrxjavademo.bean.BaseBean;
import com.zhuke.retrofitrxjavademo.impl.RetrofitService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 15653 on 2018/3/2.
 */

public class HttpUtils {

    private static HttpUtils mHttpUtils;
    private final RetrofitService mRetrofitService;

    public static HttpUtils getInstance() {
        return mHttpUtils == null ? new HttpUtils() : mHttpUtils;
    }

    public HttpUtils() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlContent.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRetrofitService = retrofit.create(RetrofitService.class);
    }

    public void sendMessage(Observable<? extends BaseBean> observable,String urlOrigin) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseBean baseBean) {

                    }
                });

    }

    public RetrofitService getRetrofitService() {
        return mRetrofitService;
    }
}
