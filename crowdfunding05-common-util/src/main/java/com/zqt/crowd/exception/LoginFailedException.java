package com.zqt.crowd.exception;

/**
 * @auther: zqtao
 * @description: 登录失败抛出的异常类
 * @Date: 2020/5/21 21:04
 * @version: 1.0
 */
public class LoginFailedException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public LoginFailedException() {
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }

    public LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
