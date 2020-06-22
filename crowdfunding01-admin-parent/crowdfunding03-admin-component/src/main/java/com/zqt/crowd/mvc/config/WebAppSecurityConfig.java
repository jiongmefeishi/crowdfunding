package com.zqt.crowd.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @auther: zqtao
 * @description: 基于注解的spring security 配置类
 * @Date: 2020/6/15
 * @version: 1.0
 */

// 标记当前类是一个配置类
@Configuration
// 标记启用 web 环境下权限控制功能
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

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
                .anyRequest()					            // 其他任意请求
                .authenticated();                   		// 认证后访问


    }


}