package com.example.spring_boot_redis_api.service;

import com.example.spring_boot_redis_api.model.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CacheService {

    private final RedisTemplate<String, Object> redisTemplate;

    public CacheService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveUser(User user) {
        redisTemplate.opsForValue().set("user:" + user.getId(), user);
    }

    public User getUser(String id) {
        return (User) redisTemplate.opsForValue().get("user:" + id);
    }

    public boolean deleteUser(String id) {
        return Boolean.TRUE.equals(redisTemplate.delete("user:" + id));
    }

    public void clearAllUsers() {
        Set<String> keys = redisTemplate.keys("user:*");
        if (!keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }
}


