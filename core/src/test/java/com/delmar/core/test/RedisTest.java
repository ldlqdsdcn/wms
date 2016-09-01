package com.delmar.core.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by admin on 2016/8/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class RedisTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Test
    public  void testRedis()
    {
        redisTemplate.opsForValue().set("name","刘大磊");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }
    @Test
    public void testGetValueFromRedis()
    {
        System.out.println(redisTemplate.opsForValue().get("name"));
        redisTemplate.delete("name");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }

}
