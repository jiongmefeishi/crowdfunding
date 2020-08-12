package com.zqt.crowd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: zqtao
 * @description: MySQL 数据微服务
 * 注解 @MapperScan 指定mybatis 接口扫描路径
 */
@MapperScan("com.zqt.crowd.mapper")
@SpringBootApplication
public class MemberMysqlProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberMysqlProviderApplication.class, args);
    }

}
