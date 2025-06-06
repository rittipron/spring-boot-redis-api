package com.example.spring_boot_redis_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testPostAndGetUser() throws Exception {
        Map<String, String> user = Map.of("id", "1", "name", "Alice");

        mockMvc.perform(post("/api/cache/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(content().string("User cached"));

        mockMvc.perform(get("/api/cache/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    void testDeleteUserEndpoint() throws Exception {
        Map<String, String> user = Map.of("id", "2", "name", "Bob");

        mockMvc.perform(post("/api/cache/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/cache/user/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("User deleted"));

        mockMvc.perform(get("/api/cache/user/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testClearAllUsersEndpoint() throws Exception {
        Map<String, String> user1 = Map.of("id", "3", "name", "Charlie");
        Map<String, String> user2 = Map.of("id", "4", "name", "Dana");

        mockMvc.perform(post("/api/cache/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/cache/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user2)))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/cache/users"))
                .andExpect(status().isOk())
                .andExpect(content().string("All cached users cleared"));

        mockMvc.perform(get("/api/cache/user/3"))
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/api/cache/user/4"))
                .andExpect(status().isNotFound());
    }
}
