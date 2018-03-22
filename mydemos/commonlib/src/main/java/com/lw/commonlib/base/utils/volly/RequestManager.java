package com.lw.commonlib.base.utils.volly;


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * 网络请求管理类
 * 
 * @author zhangfeng <br/>
 */
public class RequestManager {
	private static final String TAG = "RequestManager";

	private static RequestManager mInstance;

	private Context mAppContext;

	private RequestQueue mRequestQueue;

	private RequestManager(Context ctx) {
		this.mAppContext = ctx;
		mRequestQueue = getRequestQueue();
	}

	public static synchronized RequestManager getInstance(Context ctx) {

		if (mInstance == null) {
			mInstance = new RequestManager(ctx);
		}
		return mInstance;
	}

	public synchronized RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(mAppContext);
		}
		return mRequestQueue;
	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req, Object tag) {
		req.setTag(tag);
		getRequestQueue().add(req);
	}

	public synchronized void cancel() {
		mRequestQueue.cancelAll(TAG);
	}

	public synchronized void cancel(Object tag) {
		if (tag != null) {
			mRequestQueue.cancelAll(tag);
		}

	}
}
