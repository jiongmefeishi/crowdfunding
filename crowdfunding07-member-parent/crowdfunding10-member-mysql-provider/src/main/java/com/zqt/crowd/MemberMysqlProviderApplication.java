package com.zqt.crowd;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: zqtao
 * @description: MySQL 数据微服务
 * 注解 @MapperScan 指定mybatis 接口扫描路径
 */
@Slf4j
@MapperScan("com.zqt.crowd.mapper")
@SpringBootApplication
public class MemberMysqlProviderApplication {

    public static void main(String[] args) {
        log.info("{} 开始加载 ===>>>", "MySQL 数据微服务");
        SpringApplication.run(MemberMysqlProviderApplication.class, args);
        log.info("{} 加载完毕 ===>>>", "MySQL 数据微服务");
    }

}
