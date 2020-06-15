package com.zqt.crowd.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

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
public class WebAppSecurityConfig {

}
