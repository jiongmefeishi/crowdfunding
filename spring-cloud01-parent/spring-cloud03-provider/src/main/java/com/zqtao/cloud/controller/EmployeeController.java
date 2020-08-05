package com.zqtao.cloud.controller;

import com.zqtao.cloud.entity.Employee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return new Employee(1, "张三，小名李四", 222.2);
    }

    @RequestMapping("provider/get/employee/remote")
    Employee getEmployeeByKeyword(@RequestParam("keyword") String keyword) {
        Map<String, Employee> list = new HashMap<String, Employee>(5);
        list.put("1", new Employee(1, "王五", (double) 111));
        list.put("2", new Employee(2, "张三", (double) 222));
        list.put("3", new Employee(3, "彭超", (double) 333));

        return list.get(keyword);
    }
}
