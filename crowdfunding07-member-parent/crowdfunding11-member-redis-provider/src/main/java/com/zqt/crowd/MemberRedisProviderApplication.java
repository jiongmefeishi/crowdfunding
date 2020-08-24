package com.zqt.crowd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: zqtao
 * @description: Redis 数据微服务
 */
@Slf4j
@SpringBootApplication
public class MemberRedisProviderApplication {

    public static void main(String[] args) {
        log.info("{} 开始加载 ===>>>", "Redis 数据微服务");
        SpringApplication.run(MemberRedisProviderApplication.class, args);
        log.info("{} 加载完毕 ===>>>", "Redis 数据微服务");
    }

}
