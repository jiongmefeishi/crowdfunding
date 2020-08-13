package com.zqt.crowd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author: zqtao
 * @description: 网关微服务
 * 注解 @EnableZuulProxy 开启网关服务
 */
@EnableZuulProxy
@SpringBootApplication
public class MemberZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberZuulApplication.class, args);
    }

}
