package com.zqtao.cloud.common.api;

import com.zqtao.cloud.common.entity.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: zqtao
 * @description: 提供给 consumer 调用 provider 的 API
 * @Date: 2020/7/23
 */

// 注解开启 Feign 客户端功能，此注解开启表示当前的 API接口和一个 Provider 对应
// value 中指定要调用的 provider 的服务名
@FeignClient(value = "spring-cloud-provider")
public interface EmployeeRemoteService {

    // 远程调用的接口方法
    @RequestMapping("provider/get/employee2/remote")
    Employee getEmployee2();
}
