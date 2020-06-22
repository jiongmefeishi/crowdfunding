package com.zqt.crowd.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 1. 内存指定登录账户和登录密码，临时使用，测试登录验证
        auth
                .inMemoryAuthentication()
                .withUser("tom")
                .password("123456").roles("ADMIN")

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
                .anyRequest()                               // 其他任意请求
                .authenticated()                            // 认证后访问
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