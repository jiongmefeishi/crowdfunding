package com.zqt.crowd;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author: zqtao
 * @description: 注册中心微服务
 * 使用 @EnableEurekaServer 注解 开启Eureka 服务器功能,开启服务治理，标志当前微服务是 Eureka 注册服务中心
 * 服务端即是Eureka服务注册中心，客户端完成微服务向Eureka服务的注册与发现
 * <p>
 * 1、Eureka Server是服务端，负责管理各个微服务结点的信息和状态。
 * 2、在微服务上部署Eureka Client程序，远程访问Eureka Server将自己注册在Eureka Server。
 * 3、微服务需要调用另一个微服务时从Eureka Server中获取服务调用地址，进行远程调用。
 */
@Slf4j
@EnableEurekaServer
@SpringBootApplication
public class MemberEurekaApplication {
    public static void main(String[] args) {

        log.info("Eureka 注册中心开始加载 ===>>>");
        SpringApplication.run(MemberEurekaApplication.class, args);
        log.info("Eureka 注册中心加载完毕 ===>>>");
    }

}
