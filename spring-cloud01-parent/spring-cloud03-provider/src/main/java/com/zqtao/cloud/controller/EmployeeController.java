package com.zqtao.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zqtao.cloud.entity.Employee;
import com.zqtao.cloud.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zqtao
 * @description: 测试环境：生产者控制层
 * @date: 2020/7/7
 */
@RestController
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

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
    public Employee getEmployeeByKeyword(@RequestParam("keyword") String keyword) {

        logger.info("keyword: " + keyword);

        Map<String, Employee> list = new HashMap<String, Employee>(5);
        list.put("1", new Employee(1, "王五", (double) 111));
        list.put("2", new Employee(2, "张三", (double) 222));
        list.put("3", new Employee(3, "彭超", (double) 333));

        return list.get(keyword);
    }

    /**
     * 测试 Hystrix 服务熔断功能
     *
     * @param signal 标识该方法是否进入异常模式（是否应该启用 Hystrix 熔断功能）
     * @return ResultEntity<Employee> 数据
     *
     * 注解 @HystrixCommand 开启熔断机制，并使用 fallbackMethod 指定服务熔断后回调的备用方法
     *
     *
     * 关于熔断，其实就是调用原来的服务出现了异常或者其他问题，
     * 此时 Hystrix 会提供一个低配（替代品：原来是要一个 3 围都正点的妹子，当触发熔断机制，替换成如花也是 OK 的）
     */
    @HystrixCommand(fallbackMethod = "getEmployeeByCircuitBreakerBackup")
    @RequestMapping("provider/hystrix/get/employee/circuit/breaker")
    public ResultEntity<Employee> getEmployeeByCircuitBreaker(@RequestParam("signal") String signal) throws InterruptedException {

        String start = "start";
        if (start.equals(signal)) {
            // start 开启熔断
            throw new RuntimeException();
        }

        String sleep = "sleep";
        if (sleep.equals(signal)) {
            // 模仿超时
            Thread.sleep(5000);
        }

        return ResultEntity.successWithData(new Employee(3, "彭超", (double) 345));
    }

    public ResultEntity<Employee> getEmployeeByCircuitBreakerBackup(@RequestParam("signal") String signal) {
        return ResultEntity.failed("熔断服务机制启动 - signal : " + signal);
    }
}
