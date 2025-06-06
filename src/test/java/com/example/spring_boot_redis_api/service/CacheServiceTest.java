package com.example.spring_boot_redis_api.service;

import com.example.spring_boot_redis_api.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CacheServiceTest {

    @Autowired
    private CacheService cacheService;

    @Test
    void testCacheUser() {
        User user = new User("1", "John");
        cacheService.saveUser(user);
        Assertions.assertEquals("John", cacheService.getUser("1").getName());
    }

    @Test
    void testDeleteUser() {
        User user = new User("2", "Mike");
        cacheService.saveUser(user);
        Assertions.assertNotNull(cacheService.getUser("2"));

        boolean deleted = cacheService.deleteUser("2");
        Assertions.assertTrue(deleted);
        Assertions.assertNull(cacheService.getUser("2"));
    }

    @Test
    void testClearAllUsers() {
        cacheService.saveUser(new User("3", "Anna"));
        cacheService.saveUser(new User("4", "Jane"));

        cacheService.clearAllUsers();
        Assertions.assertNull(cacheService.getUser("3"));
        Assertions.assertNull(cacheService.getUser("4"));
    }

}
