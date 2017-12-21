package com.laika.demoservice;

import com.laika.demoservice.client.JedisClusterManager;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.JedisCluster;

/**
 * @author: 会跳舞的机器人
 * @date: 2017/12/7 13:45
 * @description:
 */
public class RedisTest extends Tester {
    @Autowired
     JedisClusterManager jedisClusterManager;

    @Test
    public void redisTest() {
        JedisCluster jedisCluster = jedisClusterManager.getJedisCluster();
        jedisCluster.set("robot","何永");
        System.out.println(jedisCluster.get("robot"));
    }
}
