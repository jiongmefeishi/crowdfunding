package com.zqt.crowd.util;

/**
 * @author: zqtao
 * @description: 封装全局项目的 ajax 请求或者远程方法调用返回的响应的数据格式
 */
public class ResultEntity<T> {

    private static final Integer SUCCESS = 0;
    private static final Integer FAILED = 500;

    public static final String RESULT_SUCCESS = "SUCCESS";
    public static final String RESULT_FAILED = "FAILED";

    /**
     * 用来封装当前请求处理的结果是成功还是失败
     */
    private String result;

    private static final String MESSAGE = "服务器错误";

    /**
     * 用来封装当前的请求结果 响应码
     */
    private Integer code;

    /**
     * 请求失败时返回的错误信息
     */
    private String msg;

    /**
     * 需要返回的数据
     */
    private T data;

    public ResultEntity() {
    }

    public ResultEntity(String result, Integer code, String msg, T data) {
        this.result = result;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    // 封装几个特殊备用方法
    // public static <E> 声明泛型 ResultEntity<E> 使用泛型 successWithoutData()

    /**
     * 请求处理成功，且不需要返回数据的工具方法
     *
     * @param <E> 声明需要使用的泛型
     */
    public static <E> ResultEntity<E> successWithoutData() {
        return new ResultEntity<E>(RESULT_SUCCESS, SUCCESS, null, null);
    }

    /**
     * 请求处理成功，需要返回数据的工具方法
     *
     * @param data 要返回的数据
     * @param <E>  声明需要使用的泛型
     */
    public static <E> ResultEntity<E> successWithData(E data) {
        return new ResultEntity<E>(RESULT_SUCCESS, SUCCESS, null, data);
    }


    /**
     * 请求处理成功，需要返回消息的工具方法
     *
     * @param msg 要返回的消息
     * @param <E> 声明需要使用的泛型
     */
    public static <E> ResultEntity<E> successWithMessage(String msg) {
        return new ResultEntity<E>(RESULT_SUCCESS, SUCCESS, msg, null);
    }


    /**
     * 请求处理失败后的工具方法
     *
     * @param message 请求失败要返回的错误信息
     * @param <E>     声明需要使用的泛型
     */
    public static <E> ResultEntity<E> failed(String message) {
        return new ResultEntity<E>(RESULT_FAILED, FAILED, message, null);
    }

    /**
     * 请求处理失败后的工具方法
     *
     * @param <E> 声明需要使用的泛型
     */
    public static <E> ResultEntity<E> failedDefault() {
        return new ResultEntity<E>(RESULT_FAILED, FAILED, MESSAGE, null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "code=" + code +
                ", message='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
