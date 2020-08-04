package com.zqtao.cloud.controller;

import com.zqtao.cloud.api.EmployeeRemoteService;
import com.zqtao.cloud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zqtao
 * @description: 使用 feign 进行 远程调用的 消费者
 * @Date: 2020/8/4
 */
@RestController
public class FeignHumanResourceController {

    @Autowired
    private EmployeeRemoteService employeeRemoteService;

    @RequestMapping("/feign/consumer/get/employee")
    public Employee getEmployee(){

        // 分析当前方法的调用过程
        // 宏观工程间数据传输过程
        // 浏览器 http://localhost:6000/feign/consumer/get/employee
        // 浏览器 给 feign-consumer 发请求 ，feign-consumer 利用common工程给provider 发请求，
        // provider把数据返回给feign-consumer，feign-consumer把数据返回给浏览器


        // 微观具体方法过程
        // http://localhost:6000/feign/consumer/get/employee
        // --> common 工程里面的 EmployeeRemoteService 的 getEmployee2() api方法
        // --> EmployeeRemoteService 利用 feign 远程调用 provider 提供的 getEmployee2() 方法
        return employeeRemoteService.getEmployee2();
    }
}
