package com.zqtao.cloud.api;

import com.zqtao.cloud.entity.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: zqtao
 * @description: 提供给 consumer 调用 provider 的 API
 * @date: 2020/7/23
 *
 *  注解开启 Feign 客户端功能，此注解开启表示当前的 API接口和一个 Provider 对应
 *  value 中指定要调用的 provider 的服务名
 */
@FeignClient(value = "spring-cloud-provider")
public interface EmployeeRemoteService {

    /**
     * 远程调用的接口方法
     * @author: zqtao
     * @return Employee
     */
    @RequestMapping("provider/get/employee2/remote")
    Employee getEmployee2();

    /**
     * 调用provider 中的获取 Employee 集合方法
     *
     * @author: zqtao
     * @param keyword 关键词
     * @return List<Employee>
     */
    @RequestMapping("provider/get/employee/list/remote")
    Employee getEmployeeByKeyword(@RequestParam("keyword") String keyword);
}
