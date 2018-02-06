package com.laika.demoservice;

import com.laika.demoservice.utils.RedisUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: 会跳舞的机器人
 * @date: 2017/12/7 13:45
    * @description:
    */
public class RedisTest extends Tester {
    @Autowired
    RedisUtils redisUtils;

    @Test
    public void redisTest() {
        redisUtils.set("robot","robot test");
        System.out.println(redisUtils.get("robot"));
    }
}
