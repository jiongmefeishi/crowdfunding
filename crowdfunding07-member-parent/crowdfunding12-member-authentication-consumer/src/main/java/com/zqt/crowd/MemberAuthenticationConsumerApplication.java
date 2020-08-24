package com.zqt.crowd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: zqtao
 * @description: 会员中心微服务
 * <p>
 * 前端页面系统在本工程
 *
 * 注解：@EnableFeignClients 启用 feign 客户端功能
 */
@Slf4j
@EnableFeignClients
@SpringBootApplication
public class MemberAuthenticationConsumerApplication {

    public static void main(String[] args) {
        log.info("{} 开始加载 ===>>>", "会员中心微服务");
        SpringApplication.run(MemberAuthenticationConsumerApplication.class, args);
        log.info("{} 加载完毕 ===>>>", "会员中心微服务");
    }

}
