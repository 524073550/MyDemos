package com.lw.commonlib.base.utils.volly;


import com.lw.commonlib.R;
import com.lw.commonlib.base.BaseApplication;

/**
 * Volley错误工具类
 * Created by luwei on 2015/12/14.
 */
public class ErrorUtils {
    /**
     * 根据错误码返回访问错误字符串
     *
     * @param type
     * @return
     */
    public static String getRequestError(int type) {
        String errorString = null;
        switch (type) {
            case ErrorConstant.NETWORK_ERROR:
                errorString = BaseApplication.getInstance().getString(R.string.net_work_error);
                break;
            case ErrorConstant.NO_CONNECTION_ERROR:
                errorString = BaseApplication.getInstance().getString(R.string.net_work_error);
                break;
            case ErrorConstant.TIMEOUT_ERROR:
                errorString = "获取数据超时";
                break;
            case ErrorConstant.SERVER_ERROR:
                errorString = "SERVER_ERROR";
                break;
            case ErrorConstant.OTHER_ERROR:
                errorString = "OTHER_ERROR";
                break;
        }
        return errorString;
    }
}
