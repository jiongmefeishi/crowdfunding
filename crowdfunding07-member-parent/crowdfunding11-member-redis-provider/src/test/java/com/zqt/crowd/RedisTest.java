package com.zqt.crowd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class RedisTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void testRedis(){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set("apple", "red");
    }

}