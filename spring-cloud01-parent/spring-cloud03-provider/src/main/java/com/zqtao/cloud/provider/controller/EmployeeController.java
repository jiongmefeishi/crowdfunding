package com.zqtao.cloud.provider.controller;

import com.zqtao.cloud.common.entity.Employee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zqtao
 * @description: 测试环境：生产者控制层
 * @Date: 2020/7/7
 */
@RestController
public class EmployeeController {

    @RequestMapping("provider/get/employee")
    public Employee getEmployee() {
        return new Employee(1, "张三", 222.2);
    }
}
