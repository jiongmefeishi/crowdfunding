package com.zqtao.cloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zqtao
 * @description: 自定义 网关 拦截 zuul 过滤
 */
@Component
public class MyZuulFilter extends ZuulFilter {


    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断当前请求是否需要进行过滤
     * 过滤，继续执行run() 方法
     * 不过滤，直接放行
     * @return true or false 是否需要过滤
     */
    public boolean shouldFilter() {

        // 1. 获取 RequestContext 对象
        RequestContext context = RequestContext.getCurrentContext();
        // 2. 获取 request 对象
        HttpServletRequest request = context.getRequest();
        // 3. 判断当前请求参数 signal 是否为 run
        String signal = "run";
        return signal.equals(request.getParameter("signal"));
    }

    public Object run() throws ZuulException {
        return null;
    }
}
