package com.zqt.crowd.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zqtao
 * @description: 判断请求类型是 Ajax 还是 普通请求
 * @Date: 2020/5/21 16:59
 * @version: 1.0
 */
public class JudgeRequestTypeUtil {

    /**
     * 判断当前请求是否为Ajax请求
     * <p>
     * 判断依据：
     * http请求时浏览器提供的Accept 和 X-Requested-With 所展示的类型
     *
     * @param request 请求对象
     * @return true：当前请求是Ajax请求
     * false：当前请求不是Ajax请求
     */
    public static boolean judgeRequestType(HttpServletRequest request) {
        // 1.获取请求消息头
        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Requested-With");

        // 2.判断
        return (acceptHeader != null && acceptHeader.contains("application/json"))

                ||

                (xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"));
    }

}
