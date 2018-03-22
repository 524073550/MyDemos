package com.lw.commonlib.base.utils.volly;

/**
 * Created by Abel on 2015/3/20.
 * 记录各种错误码
 */
public class ErrorCode {

    public static final int ERROR_DEPARTMENT_NO_DOCTOR = 1091;

    public static final int ERROR_CLOSE_CONSULT_FAIL = 1018;//结束咨询失败，问题单被举报

    public static final int ERROR_DOCTOR_NO_AUTHOR = 1112;

    public static final int BLACKLIST = 1115;//用户被拉黑

    public static final int SEND_MESSAGE_FAIL_DOCTOR_NOT_SERVICE = 1116;//消息发送失败，医生不可服务

    public static final int SEND_MESSAGE_FAIL_BLACKLIST_REPORT = 1117;//消息发送失败，这个问题单被举报

    /**
     * 发送急诊消息失败，有可能这个会话已经关闭
     */
    public static final int SEND_CONVERSATION_MESSAGE_FAIL = 1122;
}
