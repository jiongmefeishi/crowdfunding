package com.zqt.crowd;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class RedisTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Test
    void testRedis(){
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
//        operations.set("apple11", "xiaoxiao1");
        logger.info(operations.get("SMS_REDIS_CODE_PREFIX_15670255898"));
    }

}