package com.example.spring_boot_redis_api.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class PubSubService {

    private final RedisTemplate<String, Object> redisTemplate;

    public PubSubService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void publish(String message) {
        redisTemplate.convertAndSend("pubsub:channel", message);
    }
}

