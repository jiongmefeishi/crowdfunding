package com.zqt.crowd;

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
@EnableFeignClients
@SpringBootApplication
public class MemberAuthenticationConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberAuthenticationConsumerApplication.class, args);
    }

}
