package com.zqt.crowd.exception;

/**
 * @auther: zqtao
 * @description: 账号重复使用异常
 * @Date: 2020/5/28 10:39
 * @version: 1.0
 */
public class LoginAcctAlreadyInUseException extends RuntimeException {
    static final long serialVersionUID = 1L;

    public LoginAcctAlreadyInUseException() {
    }

    public LoginAcctAlreadyInUseException(String message) {
        super(message);
    }

    public LoginAcctAlreadyInUseException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctAlreadyInUseException(Throwable cause) {
        super(cause);
    }

    public LoginAcctAlreadyInUseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
