package com.zqtao.cloud.api;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zqtao.cloud.entity.Employee;
import com.zqtao.cloud.factory.MyFallBackFactory;
import com.zqtao.cloud.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: zqtao
 * @description: 提供给 consumer 调用 provider 的 API
 * @date: 2020/7/23
 * <p>
 * 注解开启 Feign 客户端功能，此注解开启表示当前的 API接口和一个 Provider 对应
 * value 中指定要调用的 provider 的服务名
 * fallbackFactory 中指定consumer 调用的provider 不可用时提供的备用方案的工厂类型
 */
@FeignClient(value = "spring-cloud-provider", fallbackFactory = MyFallBackFactory.class)
public interface EmployeeRemoteService {

    /**
     * 远程调用的接口方法
     *
     * @return Employee
     * @author: zqtao
     */
    @RequestMapping("provider/get/employee2/remote")
    Employee getEmployee2();

    /**
     * 调用provider 中的获取 Employee 集合方法
     *
     * @param keyword 关键词
     * @return List<Employee>
     * @author: zqtao
     */
    @RequestMapping("provider/get/employee/remote")
    Employee getEmployeeByKeyword(@RequestParam("keyword") String keyword);


    /**
     * 测试 Hystrix 服务熔断功能
     *
     * @param signal 标识该方法是否进入异常模式（是否应该启用 Hystrix 熔断功能）
     * @return ResultEntity<Employee> 数据
     * <p>
     * 注解 @HystrixCommand 开启熔断机制，并使用 fallbackMethod 指定服务熔断后回调的备用方法
     * <p>
     * 关于熔断，其实就是调用原来的服务出现了异常或者其他问题，
     * 此时 Hystrix 会提供一个低配（替代品：原来是要一个 3 围都正点的妹子，当触发熔断机制，替换成如花也是 OK 的）
     */
    @HystrixCommand(fallbackMethod = "getEmployeeByCircuitBreakerBackup")
    @RequestMapping("provider/hystrix/get/employee/circuit/breaker")
    ResultEntity<Employee> getEmployeeByCircuitBreaker(@RequestParam("signal") String signal);
}
