package com.zqtao.cloud.consumer.controller;

import com.zqtao.cloud.common.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: zqtao
 * @description: 测试环境：消费者
 * @Date: 2020/7/7
 */
@RestController
public class HumanResourceController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("consumer/get/employee")
    public Employee getEmployeeRemote() {

        // 远程调用方法的主机ip和端口
        String remoteHost = "http://localhost:1000/";

        // 远程调用方法的具体 URL 地址
        String url = "provider/get/employee";

        // 通过RestTemplate 调用远程的一个微服务
        return restTemplate.getForObject(remoteHost + url, Employee.class);
    }
}
