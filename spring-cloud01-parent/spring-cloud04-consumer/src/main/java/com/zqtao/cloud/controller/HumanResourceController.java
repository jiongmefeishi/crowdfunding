package com.zqtao.cloud.controller;

import com.zqtao.cloud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author: zqtao
 * @description: 测试环境：消费者
 * @date: 2020/7/7
 */
@RestController
public class HumanResourceController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 远程调用 provider 的方法，获取Employee对象信息
     *
     * @Deprecated 废弃，引入ribbon，当前使用主机ip和端口方式失效
     * @return Employee
     */
    @Deprecated
    @RequestMapping("consumer/get/employee")
    public Employee getEmployeeRemote() {

        // 远程调用方法的主机ip和端口
        String remoteHost = "http://localhost:1000/";

        // 远程调用方法的具体 URL 地址
        String url = "provider/get/employee";

        // 通过RestTemplate 调用远程的一个微服务
        return restTemplate.getForObject(remoteHost + url, Employee.class);
    }

    /**
     * 远程调用 provider 的方法，获取Employee对象信息
     *
     * 引入ribbon，使用微服务名称替换主机ip和端口方式，通过ribbon像 eureka 拉取服务信息。
     * @return Employee
     */
    @RequestMapping("consumer/ribbon/get/employee")
    public Employee getEmployeeRemoteByRibbon() {

        // 远程调用方法的主机ip和端口
        // String remoteHost = "http://localhost:1000/";

        // 引入ribbon 后，将远程调用方法的主机ip和端口
        // 改为微服务名(配置在application.yml中的spring.application.name)
        String remoteHost = "http://spring-cloud-provider";

        // 远程调用方法的具体 URL 地址
        String url = "/provider/get/employee";

        // 通过RestTemplate 调用远程的一个微服务
        return restTemplate.getForObject(remoteHost + url, Employee.class);
    }
}
