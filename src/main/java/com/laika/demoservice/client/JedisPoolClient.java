package com.laika.demoservice.client;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author: 会跳舞的机器人
 * @date: 17/12/28 下午3:52
 * @description: 获取Redis连接池工具
 */
@Slf4j
public class JedisPoolClient {
    private JedisPoolConfig jedisPoolConfig;
    private String host;
    private int port;
    private String password;
    private int timeOut;

    private JedisPool jedisPool;

    /**
     * Java bean构造函数执行之后初始化
     */
    @PostConstruct
    public void init() {
        log.info("host={},port={}",host,port);
        jedisPool = new JedisPool(jedisPoolConfig, host, port, timeOut, password);
    }

    /**
     * 获取Redis连接池
     *
     * @return
     */
    public JedisPool getJedisPool() {
        if (jedisPool == null) {
            init();
        }
        return jedisPool;
    }

    /**
     * 应用关闭时,关闭连接池
     */
    public void destroy() {
        if (jedisPool != null) {
            jedisPool.close();
        }
    }

    public void setJedisPoolConfig(JedisPoolConfig jedisPoolConfig) {
        this.jedisPoolConfig = jedisPoolConfig;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }
}
