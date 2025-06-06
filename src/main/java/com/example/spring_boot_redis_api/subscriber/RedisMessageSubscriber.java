package com.example.spring_boot_redis_api.subscriber;

import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Component;

@Component
public class RedisMessageSubscriber {

    public void onMessage(Message message, byte[] pattern) {
        System.out.println("Received: " + message.toString());
    }
}
