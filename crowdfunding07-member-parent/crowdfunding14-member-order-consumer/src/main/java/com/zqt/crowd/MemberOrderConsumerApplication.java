package com.zqt.crowd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: zqtao
 * @description: 订单维护微服务
 */
@Slf4j
@EnableFeignClients
@SpringBootApplication
public class MemberOrderConsumerApplication {

    public static void main(String[] args) {
        log.info("{} 开始加载 ===>>>", "订单维护微服务");
        SpringApplication.run(MemberOrderConsumerApplication.class, args);
        log.info("{} 加载完毕 ===>>>", "订单维护微服务");
    }

}
