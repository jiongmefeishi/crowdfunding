package com.zqt.crowd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: zqtao
 * @description: 项目维护微服务
 */
@Slf4j
@EnableFeignClients
@SpringBootApplication
public class MemberProjectConsumerApplication {
    public static void main(String[] args) {
        log.info("{} 开始加载 ===>>>", "项目维护微服务");
        SpringApplication.run(MemberProjectConsumerApplication.class, args);
        log.info("{} 加载完毕 ===>>>", "项目维护微服务");
    }
}
