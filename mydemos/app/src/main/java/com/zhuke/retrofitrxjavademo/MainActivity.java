package com.zhuke.retrofitrxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.zhuke.retrofitrxjavademo.bean.PostInfo;
import com.zhuke.retrofitrxjavademo.impl.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.kuaidi100.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<PostInfo> yuantong = retrofitService.getPostInfo("yuantong", "11111111111");
        yuantong.enqueue(new Callback<PostInfo>() {
            @Override
            public void onResponse(Call<PostInfo> call, Response<PostInfo> response) {
                Log.i("http返回：", response.body().toString() + "");
            }

            @Override
            public void onFailure(Call<PostInfo> call, Throwable t) {

            }
        });
    }
}
