package com.zqt.crowd.mvc.config;

import com.zqt.crowd.constant.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zqtao
 * @description: 基于注解的spring security 配置类
 * @Date: 2020/6/15
 * @version: 1.0
 */

// 标记当前类是一个配置类
@Configuration
// 标记启用 web 环境下权限控制功能
@EnableWebSecurity
// 标记启用全局方法权限控制功能 并且设置prePostEnabled = true。
// 保证@PreAuthority、@PostAuthority、@PreFilter、@PostFilter生效
// 此注解开启，可使用上述注解对方法进行权限控制，访问某个方法需要某个权限
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityUserDetailService securityUserDetailService;

    // 装配在 spring-web-mvc.xml 中声明的spring security 盐值加密所需 bean
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//         1. 内存指定登录账户和登录密码，临时使用，测试登录验证
//        auth
//                .inMemoryAuthentication()
//                .withUser("tom")
//                .password("123456").roles("ADMIN")
//        ;

        // 2. 使用数据库中信息进行登陆验证
        auth
                .userDetailsService(securityUserDetailService) // 登录验证
                .passwordEncoder(passwordEncoder)
        ;
    }

    // 请求授权
    @Override
    protected void configure(HttpSecurity security) throws Exception {

        security
                // 1.开放登录页面
                .authorizeRequests() // 对请求进行授权
                .antMatchers("/admin/to/login/page.html") // 开放登录页面
                .permitAll()                                // 无条件访问，不做任何拦截
                // 2.开放静态资源
                .antMatchers("/bootstrap/**")    // 针对静态资源进行设置，无条件访问
                .permitAll()
                .antMatchers("/css/**")          // 针对静态资源进行设置，无条件访问
                .permitAll()
                .antMatchers("/fonts/**")        // 针对静态资源进行设置，无条件访问
                .permitAll()
                .antMatchers("/img/**")          // 针对静态资源进行设置，无条件访问
                .permitAll()
                .antMatchers("/jquery/**")       // 针对静态资源进行设置，无条件访问
                .permitAll()
                .antMatchers("/layer/**")        // 针对静态资源进行设置，无条件访问
                .permitAll()
                .antMatchers("/script/**")       // 针对静态资源进行设置，无条件访问
                .permitAll()
                .antMatchers("/ztree/**")        // 针对静态资源进行设置，无条件访问
                .permitAll()
                .antMatchers("/zqtScript/**")    // 针对静态资源进行设置，无条件访问
                .permitAll()
//                .antMatchers("/admin/get/page.html")      // 针对用户分页显示页面，限制管理员访问，或者拥有 user:get权限
//                .hasRole("管理员")
//                .hasAuthority("user:get")
//                .access("hasRole('管理员') OR hasAuthority('user:get')")
                .anyRequest()                               // 其他任意请求
                .authenticated()                            // 认证后访问
                // 自定义异常拦截
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request,
                                       HttpServletResponse response,
                                       AccessDeniedException e) throws IOException, ServletException {
                        // 配置异常抛出信息
                        request.setAttribute("exception", new Exception(CommonConstant.MESSAGE_ACCESS_DENIED));
                        // 配置异常统一处理页面地址
                        request.getRequestDispatcher("/WEB-INF/system-error.jsp").forward(request, response);
                    }
                })
                // 3.指定跨站请求伪造(csrf)的防护手段
                .and()
                .csrf()                                     // 防跨站请求伪造功能
                // 禁用csrf 功能(之前未引入spring security模块时没有加上CSRF，导致测试无法访问)
                // 无法通过认证 Could not verify the provided CSRF token because your session was not found.
                .disable()
                // 4.开启登录认证
                .formLogin()                                    // 开启表单登录功能
                .loginPage("/admin/to/login/page.html")         // 指定登录页面
                .loginProcessingUrl("/security/do/login.html")  // 指定处理登录请求的地址，交给spring security处理，不需要具体实现
                .defaultSuccessUrl("/admin/to/main/page.html")  // 指定登录成功跳转地址
                .usernameParameter("loginAcct")                 // 指定登录请求的账号参数
                .passwordParameter("userPswd")                  // 指定登录请求的密码参数
                .and()
                .logout()                                       // 开启退出登录功能
                .logoutUrl("/security/do/logout.html")          // 指定退出登录处理的地址，交给spring security处理，不需要具体实现
                .logoutSuccessUrl("/admin/to/login/page.html")  // 指定退出成功以后前往的地址
        ;


    }

}