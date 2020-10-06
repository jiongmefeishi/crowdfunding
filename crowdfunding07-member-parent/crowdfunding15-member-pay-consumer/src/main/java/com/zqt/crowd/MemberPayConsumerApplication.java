package com.zqt.crowd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: zqtao
 * @description: 支付功能微服务
 */
@Slf4j
@EnableFeignClients
@SpringBootApplication
public class MemberPayConsumerApplication {

    public static void main(String[] args) {
        log.info("{} 开始加载 ===>>>", "支付功能微服务");
        SpringApplication.run(MemberPayConsumerApplication.class, args);
        log.info("{} 加载完毕 ===>>>", "支付功能微服务");
    }

}
