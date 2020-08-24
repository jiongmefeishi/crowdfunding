package com.zqt.crowd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author: zqtao
 * @description: 网关微服务
 * 注解 @EnableZuulProxy 开启网关服务
 */
@Slf4j
@EnableZuulProxy
@SpringBootApplication
public class MemberZuulApplication {

    public static void main(String[] args) {
        log.info("{} 开始加载 ===>>>", "网关微服务");
        SpringApplication.run(MemberZuulApplication.class, args);
        log.info("{} 加载完毕 ===>>>", "网关微服务");
    }

}
