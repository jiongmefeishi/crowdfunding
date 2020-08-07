package com.zqtao.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zqtao
 * <p>
 * 当前两个注解在低版本 Spring Cloud 中需要，用于开启 Eureka Client 功能
 * 注解 @EnableDiscoveryClient // 启用发现服务功能，注册中心可以是其他的，如 Zookeeper
 * 注解 @EnableEurekaClient // 启用Eureka 客户端功能，注册中心是 Eureka
 * <p>
 * 注解 @EnableCircuitBreaker // 开启 Hytrix 的服务熔断功能, 服务熔断是provider 端使用的，当被请求的服务异常，提供替代服务
 */
@EnableCircuitBreaker
@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

}
