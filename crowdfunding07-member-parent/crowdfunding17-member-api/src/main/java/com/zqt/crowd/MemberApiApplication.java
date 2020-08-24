package com.zqt.crowd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: zqtao
 * @description: API 模块微服务
 */
@Slf4j
@SpringBootApplication
public class MemberApiApplication {

    public static void main(String[] args) {
        log.info("{} 开始加载 ===>>>", "API 模块微服务");
        SpringApplication.run(MemberApiApplication.class, args);
        log.info("{} 加载完毕 ===>>>", "API 模块微服务");
    }

}
