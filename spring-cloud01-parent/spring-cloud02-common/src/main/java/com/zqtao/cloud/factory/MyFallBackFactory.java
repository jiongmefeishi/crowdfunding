package com.zqtao.cloud.factory;

import com.zqtao.cloud.api.EmployeeRemoteService;
import com.zqtao.cloud.entity.Employee;
import com.zqtao.cloud.util.ResultEntity;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author: zqtao
 * @description: 服务降级备选方案(当前是给 EmployeeRemoteService 接口的api 提供的备选方案)
 * 该类实现Consumer端服务降级功能
 * 实现FallBbackFactory 接口时要传入 @FeignClient 注解标记的接口类型
 * 在create() 方法中返回 @FeignClient注解表姐的接口类型的对象
 * 当provider 调用失败后，会执行这个对象的对应方法
 *
 * <p>
 * 请注意自动扫描包的规则
 * 比如：feign-consumer 工程需要使用 MyFallBackFactory，
 * 那么 MyFallBackFactory 应该在 feign-consumer 工程的主启动类所在包或它的子包下
 * 简单来说：哪个工程用这个类，哪个工程必须想办法扫描到这个类
 */
@Component
public class MyFallBackFactory implements FallbackFactory<EmployeeRemoteService> {

    public EmployeeRemoteService create(final Throwable throwable) {

        // 提供内部类，实现备选方案
        return new EmployeeRemoteService() {
            public Employee getEmployee2() {
                return new Employee(1, "我是getEmployee2 API 的备选方案：彭超", (double) 333);
            }

            public Employee getEmployeeByKeyword(String keyword) {
                return new Employee(3, "我是getEmployeeByKeyword(String keyword) API 的备选方案：孙舒扬", (double) 777);
            }

            public ResultEntity<Employee> getEmployeeByCircuitBreaker(String signal) {
                return ResultEntity.failed(throwable.getMessage() + "触发降级机制，我是getEmployeeByCircuitBreaker API 的备选方案，signal : " + signal);
            }
        };
    }
}
