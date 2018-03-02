package com.zhuke.retrofitrxjavademo.impl;

import com.zhuke.retrofitrxjavademo.bean.PostInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 15653 on 2018/3/1.
 */

public interface RetrofitService {
        @GET("query")
        Call<PostInfo> getPostInfo(@Query("type")String type, @Query("postId")String postId);


        /**
         * 获取快递信息
         * Rx方式
         *
         * @param type   快递类型
         * @param postid 快递单号
         * @return Observable<PostInfo>
         */
        @GET("query")
        Observable<PostInfo> getPostInfoRx(@Query("type") String type, @Query("postid") String postid);
}
