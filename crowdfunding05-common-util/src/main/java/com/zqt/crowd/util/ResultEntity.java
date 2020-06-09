package com.zqt.crowd.util;

/**
 * @auther: zqtao
 * @description: 封装规范化 Ajax 请求返回的结果格式
 * @Date: 2020/5/21 16:09
 * @version: 1.0
 */
public class ResultEntity<T> {

    private static final String SUCCESS = "SUCCESS";
    private static final String FAILED = "FAILED";

    /**
     * 用来封装当前的请求结果是success or error
     */
    private String result;

    /**
     * 请求失败时返回的错误信息
     */
    private String message;

    /**
     * 需要返回的数据
     */
    private T data;

    public ResultEntity() {
    }

    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
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
        return new ResultEntity<E>(SUCCESS, null, null);
    }

    /**
     * 请求处理成功，需要返回数据的工具方法
     *
     * @param data 要返回的数据
     * @param <E>  声明需要使用的泛型
     */
    public static <E> ResultEntity<E> successWithData(E data) {
        return new ResultEntity<E>(SUCCESS, null, data);
    }

    /**
     * 请求处理失败后的工具方法
     * @param message 请求失败要返回的错误信息
     * @param <E> 声明需要使用的泛型
     * @return
     */
    public static <E> ResultEntity<E> failed(String message) {
        return new ResultEntity<E>(FAILED, message, null);
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
