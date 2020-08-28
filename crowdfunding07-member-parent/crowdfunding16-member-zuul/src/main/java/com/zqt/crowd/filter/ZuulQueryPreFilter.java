package com.zqt.crowd.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.zqt.crowd.constant.CommonConstant;
import com.zqt.crowd.constant.GatewayZuulNotFilterResourceConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author: zqtao
 * @description: zuul 网关请求过滤器，打印所有走网关的请求信息
 *
 *
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

    /**
     * zuul 网关检查是否需要进行登录过滤
     */
    public boolean shouldFilter() {
        log.info(
                "执行方法: {} ，方法描述: {} \n",
                "ZuulQueryPreFilter shouldFilter",
                "zuul 网关检查是否需要进行登录过滤"
        );

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

        // 配置请求拦截
        // 获取servletPath
        String servletPath = request.getServletPath();
        // 判断当前请求是否是无需过滤的请求
        boolean contains = GatewayZuulNotFilterResourceConstant.PASS_RES_SET.contains(servletPath);
        // 如果是无需过滤的请求，返回false
        if (contains) {
            log.info("当前请求是无需进行登录过滤的请求===>>  " + servletPath);
            return false;
        }

        // 判断当前请求是否是请求静态资源
        boolean staticResource = GatewayZuulNotFilterResourceConstant.judgeCurrentServletPathWhetherStaticResource(servletPath);
        // 如果是静态资源，返回false
        if (staticResource) {
            log.info("请求静态资源无需过滤 ===>>> " + servletPath);
        }
        return !staticResource;
    }

    /**
     * 执行登录过滤
     */
    public Object run() {

        log.info(
                "执行方法: {} ，方法描述: {} \n",
                "ZuulQueryPreFilter run",
                "执行登录过滤"
        );

        // 1. 获取 RequestContext 对象
        RequestContext context = RequestContext.getCurrentContext();
        // 2. 获取 request 对象
        HttpServletRequest request = context.getRequest();

        // 3. 获取session 对象
        HttpSession session = request.getSession();

        // 4. 从session 中获取已登录用户信息
        Object loginUser = session.getAttribute(CommonConstant.MESSAGE_LOGIN_USER_MEMBER);

        // 判断当前用户是否为空
        if(loginUser == null) {
            // 为空，表示当前用户没有登录，重定向到登录页面
            // 当前工程是网关工程 member-zuul
            // 登录页面在member-authentication-consumer 工程
            // 不能使用 return "redirect:/auth/member/to/login/page"; 进行重定向
            // 因为是重定向，那么是两个请求，返回的消息提示不能放进request中，需要放进session域

            // 获取 response 对象
            HttpServletResponse response = context.getResponse();
            // 将消息提示存储进session 域
            session.setAttribute(CommonConstant.ATTR_NAME_MESSAGE, CommonConstant.MESSAGE_ACCESS_FORBIDEN);
            // 重定向
            try {
                log.info("\n\n{} : " + "/auth/member/to/login/page", "当前用户没有登录，重定向到登录页面");
                response.sendRedirect("/auth/member/to/login/page");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }
}
