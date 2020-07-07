package com.zqtao.cloud.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: zqtao
 * @description: 创建 Template 通用配置类,提供 RestTemplate,用于远程调用（测试使用，正式使用）
 * @Date: 2020/7/7
 */
@Configuration
public class MyTemplate {

    /**
     * 提供 RestTemplate Bean
     * @return new RestTemplate();
     */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
