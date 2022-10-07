package com.kytc.bikeID.service.impl;

import com.kytc.bikeID.service.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RequiredArgsConstructor
@Service
@Log4j2
public class CacheServiceImpl implements CacheService {

    private final JedisPool jedisPool = new JedisPool("172.22.13.153", 6379);

    @Override
    public String addValue(String key, String value) {

        if (log.isDebugEnabled()) log.debug("Cache add key: " + key + ", value: " + value);
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.set(key, value);
        }
    }

    @Override
    public String addValueExpired(String key, String value, int seconds) {

        if (log.isDebugEnabled())
            log.debug("Cache add expired key: " + key + ", value: " + value + ", expired: " + seconds);
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.setex(key, seconds, value);
        }

        return value;
    }

    @Override
    public String getValueByKey(String key) {

        Jedis jedis = jedisPool.getResource();
        return jedis.get(key);
    }

}
