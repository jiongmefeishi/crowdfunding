package com.zqtao.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// 当前两个注解在低版本 Spring Cloud 中需要，用于开启 Eureka Client 功能
//@EnableDiscoveryClient // 启用发现服务功能，注册中心可以是其他的，如 Zookeeper
//@EnableEurekaClient // 启用Eureka 客户端功能，注册中心是 Eureka
@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

}
