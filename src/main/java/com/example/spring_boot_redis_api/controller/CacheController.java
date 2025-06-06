package com.example.spring_boot_redis_api.controller;

import com.example.spring_boot_redis_api.model.User;
import com.example.spring_boot_redis_api.service.CacheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cache")
public class CacheController {

    private final CacheService cacheService;

    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @PostMapping("/user")
    public ResponseEntity<String> cacheUser(@RequestBody User user) {
        cacheService.saveUser(user);
        return ResponseEntity.ok("User cached");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        User user = cacheService.getUser(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        boolean deleted = cacheService.deleteUser(id);
        return deleted ? ResponseEntity.ok("User deleted") : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/users")
    public ResponseEntity<String> clearAllUsers() {
        cacheService.clearAllUsers();
        return ResponseEntity.ok("All cached users cleared");
    }
}

