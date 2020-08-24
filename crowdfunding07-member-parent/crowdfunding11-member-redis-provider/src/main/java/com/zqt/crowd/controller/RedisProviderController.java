package com.zqt.crowd.controller;

import com.zqt.crowd.util.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zqtao
 * @description: redis 操作控制层，向外提供暴露接口
 */
@Slf4j
@RestController
public class RedisProviderController {

    @Autowired
    private StringRedisTemplate redisTemplate;


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
    ) {
        log.info(
                "执行方法: {} ，方法描述: {} \n",
                "setRedisKeyValueRemote",
                "向 redis 中存储键值对"
        );
        ValueOperations<String, String> operations = redisTemplate.opsForValue();

        try {
            operations.set(key, value);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

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
    ) {
        log.info(
                "执行方法: {} ，方法描述: {} \n",
                "setRedisKeyValueWithTimeoutRemote",
                "向 redis 中存储键值对"
        );
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        try {
            operations.set(key, value, timeout, timeUnix);

            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * 根据 key 获取 value
     *
     * @param key 键值
     * @return value
     */
    @GetMapping(value = "get/redis/string/value/by/key/remote")
    ResultEntity<String> getRedisStringValueByKeyRemote(@RequestParam("key") String key) {
        log.info(
                "执行方法: {} ，方法描述: {} \n",
                "getRedisStringValueByKeyRemote",
                "根据 key 获取 value"
        );
        ValueOperations<String, String> operations = redisTemplate.opsForValue();

        try {
            String value = operations.get(key);
            return ResultEntity.successWithData(value);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    }

    /**
     * 根据键值删除一条 redis 记录
     *
     * @param key 键值
     * @return ResultEntity<String>
     */
    @RequestMapping("remove/redis/key/remote")
    ResultEntity<String> removeRedisKeyRemote(@RequestParam("key") String key) {
        log.info(
                "执行方法: {} ，方法描述: {} \n",
                "removeRedisKeyRemote",
                "根据键值删除一条 redis 记录"
        );
        try {
            Boolean delete = redisTemplate.delete(key);
            if (delete != null && delete) {
                return ResultEntity.successWithoutData();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
        return ResultEntity.failedDefault();
    }

}
