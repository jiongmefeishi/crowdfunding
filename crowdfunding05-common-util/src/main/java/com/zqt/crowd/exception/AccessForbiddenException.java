package com.zqt.crowd.exception;

/**
 * @auther: zqtao
 * @description: 表示用户没有登录就访问受保护资源时抛出的异常
 * @Date: 2020/5/23 12:48
 * @version: 1.0
 */
public class AccessForbiddenException extends RuntimeException{



    public AccessForbiddenException() {
    }

    public AccessForbiddenException(String message) {
        super(message);
    }

    public AccessForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenException(Throwable cause) {
        super(cause);
    }

    public AccessForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
