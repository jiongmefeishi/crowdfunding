package com.zqt.crowd.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author: zqtao
 * @description: zuul 网关请求过滤器，打印所有走网关的请求信息
 */
@Component
@Slf4j
public class ZuulQueryPreFilter extends ZuulFilter {
    @Override
    public int filterOrder() {
        // run before PreDecoration
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    public boolean shouldFilter() {
        // 1. 获取 RequestContext 对象
        RequestContext context = RequestContext.getCurrentContext();
        // 2. 获取 request 对象
        HttpServletRequest request = context.getRequest();
        // 获取客户端发出请求时的完整URL
        StringBuffer requestURL = request.getRequestURL();
        // 获取发出请求的客户机的IP地址
        String remoteAddr = request.getRemoteAddr();
        // 获取请求行中的参数部分（参数名+值）
        String queryString = request.getQueryString();

        log.info("===>>> 客户端发出请求时的完整URL: {}", requestURL);
        log.info("===>>> 请求的客户机的IP地址: {}", remoteAddr);
        log.info("===>>> 请求参数: {}", queryString);

        return false;
    }

    public Object run() {
        return null;
    }
}
