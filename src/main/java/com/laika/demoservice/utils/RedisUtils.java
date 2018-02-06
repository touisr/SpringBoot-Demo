package com.laika.demoservice.utils;

import com.laika.demoservice.client.JedisPoolClient;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * @author: 会跳舞的机器人
 * @date: 17/12/28 下午4:28
 * @description: Redis基本操作
 */
@Component
public class RedisUtils {

    //服务商缓存KEY
    public static final String REDIS_KEY_PREFIX_AGENT = "USERSERVICE:AGENT:";

    @Autowired
    private JedisPoolClient jedisPoolClient;

    public String set(String key, String value) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisPoolClient.getJedisPool().getResource();
            result = jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    public String setex(String key, String value, int seconds) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisPoolClient.getJedisPool().getResource();
            result = jedis.setex(key, seconds, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        jedis.close();
        return result;
    }


    public String get(String key) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisPoolClient.getJedisPool().getResource();
            result = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    // 查找，返回Hash
    public Map<String, String> hashGet(String key) {
        Jedis jedis = null;
        Map<String, String> result = null;
        try {
            jedis = jedisPoolClient.getJedisPool().getResource();
            result = jedis.hgetAll(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    public long hset(String key, String field, String value) {
        Jedis jedis = null;
        long result = 0;
        try {
            jedis = jedisPoolClient.getJedisPool().getResource();
            result = jedis.hset(key, field, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }


    public long hdel(String key, String field) {
        Jedis jedis = null;
        long result = 0;
        try {
            jedis = jedisPoolClient.getJedisPool().getResource();
            result = jedis.hdel(key, field);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    public String hsetAll(String key, Map<String, String> map) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisPoolClient.getJedisPool().getResource();
            result = jedis.hmset(key, map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    public String hget(final String key, final String field) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisPoolClient.getJedisPool().getResource();
            result = jedis.hget(key, field);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }


    public Boolean hexists(final String key, final String field) {
        Jedis jedis = null;
        Boolean flag = null;
        try {
            jedis = jedisPoolClient.getJedisPool().getResource();
            flag = jedis.hexists(key, field);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return flag;
    }

    public long expireAt(String key, long unixTime) {
        Jedis jedis = null;
        long result = 0;
        try {
            jedis = jedisPoolClient.getJedisPool().getResource();
            result = jedis.expireAt(key, unixTime);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    public long expire(String key, int second) {
        Jedis jedis = null;
        long result = 0;
        try {
            jedis = jedisPoolClient.getJedisPool().getResource();
            result = jedis.expire(key, second);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }


    public void remove(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPoolClient.getJedisPool().getResource();
            jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
