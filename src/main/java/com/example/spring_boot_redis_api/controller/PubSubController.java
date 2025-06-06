package com.example.spring_boot_redis_api.controller;

import com.example.spring_boot_redis_api.service.PubSubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/pubsub")
public class PubSubController {

    private final PubSubService pubSubService;

    public PubSubController(PubSubService pubSubService) {
        this.pubSubService = pubSubService;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody Map<String, String> body) {
        pubSubService.publish(body.get("message"));
        return ResponseEntity.ok("Message published");
    }
}

