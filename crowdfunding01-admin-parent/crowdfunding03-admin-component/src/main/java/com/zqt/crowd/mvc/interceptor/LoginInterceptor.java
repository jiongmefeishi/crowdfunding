package com.zqt.crowd.mvc.interceptor;

import com.zqt.crowd.constant.CommonConstant;
import com.zqt.crowd.entity.admin.Admin;
import com.zqt.crowd.exception.AccessForbiddenException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: zqtao
 * @description: 登录验证拦截器，校验用户是否登录
 * 需要去spring-web-mvc.xml 配置文件中配置注册拦截器
 * @Date: 2020/5/23 13:01
 * @version: 1.0
 *
 * @supplement: 补充说明，当前拦截器废弃使用，使用 spring security 自行拦截请求
 * @Deprecated 弃用
 */
@Deprecated
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        // 1.通过request对象获取Session对象
        HttpSession session = request.getSession();

        // 2.尝试从Session域中获取Admin对象
        Admin admin = (Admin) session.getAttribute(CommonConstant.ATTR_NAME_LOGIN_ADMIN);

        // 3.判断admin对象是否为空
        if(admin == null) {
            // 4.抛出异常
            throw new AccessForbiddenException(CommonConstant.MESSAGE_ACCESS_FORBIDEN);
        }

        // 5.如果Admin对象不为null，则返回true放行
        return true;
    }
}
