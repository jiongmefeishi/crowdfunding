package com.zqtao.cloud.provider.controller;

import com.zqtao.cloud.common.entity.Employee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zqtao
 * @description: 测试环境：生产者控制层
 * @Date: 2020/7/7
 */
@RestController
public class EmployeeController {

    @RequestMapping("provider/get/employee")
    public Employee getEmployee(HttpServletRequest request) throws InterruptedException {
        // 获取当前 web 应用的端口号
        int serverPort = request.getServerPort();

        wait(1000);

        return new Employee(1, "张三 + port: " + serverPort, 222.2);
    }

    @RequestMapping("provider/get/employee2/remote")
    public Employee getEmployee2() {
        return new Employee(1, "张三", 222.2);
    }
}
