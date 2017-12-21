package com.laika.demoservice.client;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class JedisClusterManager {
    private static final int DEFAULT_TIMEOUT = 1000;
    private static final int DEFAULT_MAX_REDIRECTIONS = 20;
    private static final int MAX_TOTAL = 100;
    private static final int MAX_IDLE = 40;
    private static final int MIN_IDLE = 20;
    private static final int MAX_WAITE_MILLIS = 30000;
    private static final boolean TEST_ON_BORROW = true;
    private static final boolean TEST_WHILE_IDLE = true;
    private int maxTotal = 100;
    private int maxIdle = 40;
    private int minIdle = 20;
    private int maxWaiteMillis = 30000;
    private boolean testOnBorrow = true;
    private boolean testWhileIdle = true;
    private String servers;
    private int timeout = 1000;
    private int maxRedirections = 20;
    private JedisCluster jedisCluster;

    public JedisClusterManager() {
    }

    public void init() {
        Set<HostAndPort> jedisClusterNodes = new HashSet();
        String[] arr$ = this.servers.split("[,]");
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            String server = arr$[i$];
            String[] sa = server.split("[:]");
            if (sa.length == 2) {
                String host = sa[0];
                int port = Integer.parseInt(sa[1]);
                jedisClusterNodes.add(new HostAndPort(host, port));
            }
        }
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(this.maxTotal);
        jedisPoolConfig.setMaxIdle(this.maxIdle);
        jedisPoolConfig.setMinIdle(this.minIdle);
        jedisPoolConfig.setMaxWaitMillis((long) this.maxWaiteMillis);
        jedisPoolConfig.setTestOnBorrow(this.testOnBorrow);
        jedisPoolConfig.setTestWhileIdle(this.testWhileIdle);
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes, this.timeout, this.maxRedirections, jedisPoolConfig);
        this.jedisCluster = jedisCluster;
    }

    public JedisCluster getJedisCluster() {
        if (null == this.jedisCluster) {
            synchronized (this) {
                if (null == this.jedisCluster) {
                    this.init();
                }
            }
        }
        return this.jedisCluster;
    }

    public int getMaxTotal() {
        return this.maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return this.maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return this.minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxWaiteMillis() {
        return this.maxWaiteMillis;
    }

    public void setMaxWaiteMillis(int maxWaiteMillis) {
        this.maxWaiteMillis = maxWaiteMillis;
    }

    public boolean isTestOnBorrow() {
        return this.testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestWhileIdle() {
        return this.testWhileIdle;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public String getServers() {
        return this.servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getMaxRedirections() {
        return this.maxRedirections;
    }

    public void setMaxRedirections(int maxRedirections) {
        this.maxRedirections = maxRedirections;
    }

}
