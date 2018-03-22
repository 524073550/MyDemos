package com.lw.commonlib.base.utils.volly;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;

/**
 * volley字符类
 */
public class VolleyStringRequest extends Request<String> {
    private final Listener<String> mListener;
    protected RequestErrorListener mRequestErrorListener;
    private int timeoutMs = 20 * 1000;// 20秒

    private long startTime;

    /**
     * Creates a new request with the given method.
     *
     * @param method   the request {@link Method} to use
     * @param url      URL to fetch the string at
     * @param listener Listener to receive the String response
     */
    public VolleyStringRequest(int method, String url, Listener<String> listener, RequestErrorListener requestErrorListener) {
        super(method, url, null);
        mListener = listener;
        mRequestErrorListener = requestErrorListener;
        startTime = System.currentTimeMillis();
        setRetryPolicy(new DefaultRetryPolicy(timeoutMs, 1, 1.0f));
    }

    /**
     * Creates a new GET request.
     *
     * @param url      URL to fetch the string at
     * @param listener Listener to receive the String response
     */
    public VolleyStringRequest(String url, Listener<String> listener, RequestErrorListener requestErrorListener) {
        this(Method.GET, url, listener, requestErrorListener);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }

    @Override
    public void deliverError(VolleyError error) {
        //super.deliverError(error);
        int errorType;
        if (error instanceof NetworkError) {
            errorType = ErrorConstant.NETWORK_ERROR;
        } else if (error instanceof TimeoutError) {
            errorType = ErrorConstant.TIMEOUT_ERROR;
        } else if (error instanceof ServerError) {
            errorType = ErrorConstant.SERVER_ERROR;
        } else {
            errorType = ErrorConstant.OTHER_ERROR;
        }
        mRequestErrorListener.errorResponse(errorType);
    }

    public interface RequestErrorListener {
        public void errorResponse(int type);
    }

}
