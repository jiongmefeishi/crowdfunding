package com.zqt.crowd.api;

import com.zqt.crowd.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.TimeUnit;

/**
 * @author: zqtao
 * @description: redis 远程服务调用接口
 * 利用feign实现远程调用member-mysql-provider 微服务中提供的接口
 * <p>
 * 注解 @FeignClient 开启 Feign 客户端功能，此注解开启表示当前的 API接口和一个 Provider 对应
 * value 中指定要调用的 provider 的服务名
 * fallbackFactory 中指定 consumer 调用的 provider 不可用时提供的备用方案的工厂类型s
 */
@FeignClient(value = "crowdfunding-member-redis-provider")
public interface RedisRemoteApi {


    /**
     * 向 redis 中存储键值对
     *
     * @param key   redis key
     * @param value redis value
     * @return ResultEntity<String>
     */
    @RequestMapping(value = "set/redis/key/value/remote")
    ResultEntity<String> setRedisKeyValueRemote(
            @RequestParam("key") String key,
            @RequestParam("value") String value
    );

    /**
     * 向 redis 中存储键值对
     *
     * @param key      redis key
     * @param value    redis value
     * @param timeout  过期时间
     * @param timeUnix 时间级别（如：小时，分，秒）
     * @return ResultEntity<String>
     */
    @RequestMapping(value = "set/redis/key/value/with/timeout/remote")
    ResultEntity<String> setRedisKeyValueWithTimeoutRemote(
            @RequestParam("key") String key,
            @RequestParam("value") String value,
            @RequestParam("timeout") long timeout,
            @RequestParam("timeUnix") TimeUnit timeUnix
    );

    /**
     * 根据 key 获取 value
     *
     * @param key 键值
     * @return value
     */
    @GetMapping(value = "get/redis/string/value/by/key/remote")
    ResultEntity<String> getRedisStringValueByKeyRemote(@RequestParam("key") String key);

    /**
     * 根据键值删除一条 redis 记录
     * @param key 键值
     * @return ResultEntity<String>
     */
    @RequestMapping("remove/redis/key/remote")
    ResultEntity<String> removeRedisKeyRemote(@RequestParam("key") String key);

}
