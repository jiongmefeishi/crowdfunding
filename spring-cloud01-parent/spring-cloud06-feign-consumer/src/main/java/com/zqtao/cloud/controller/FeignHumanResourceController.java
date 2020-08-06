package com.zqtao.cloud.controller;

import com.zqtao.cloud.api.EmployeeRemoteService;
import com.zqtao.cloud.entity.Employee;
import com.zqtao.cloud.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: zqtao
 * @description: 使用 feign 进行 远程调用的 消费者
 * @date: 2020/8/4
 */
@RestController
public class FeignHumanResourceController {

    @Autowired
    private EmployeeRemoteService employeeRemoteService;

    /**
     * 利用 feign 实现远程调用 provider 中的方法
     *
     * @return Employee
     */
    @RequestMapping("/feign/consumer/get/employee")
    public Employee getEmployeeRemote() {

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

    /**
     * 利用 feign 实现远程调用 provider 中的方法
     *
     * @return Employee
     */
    @RequestMapping("feign/consumer/search")
    public Employee getEmployeeListRemote(String keyword) {
        return employeeRemoteService.getEmployeeByKeyword(keyword);
    }


    /**
     * 测试 feign 的服务降级功能
     *
     * @param signal provider 端需要的信号， start 表示开启provider 端的熔断功能
     * @return ResultEntity<Employee>
     *
     * <p>
     * 测试方式：1、正常访问 provider 服务，2、人为关闭 provider 服务
     */
    @RequestMapping("feign/consumer/test/fallback")
    public ResultEntity<Employee> getEmployeeWithFallBackFactory(@RequestParam("signal") String signal) {
        return employeeRemoteService.getEmployeeByCircuitBreaker(signal);
    }


}
