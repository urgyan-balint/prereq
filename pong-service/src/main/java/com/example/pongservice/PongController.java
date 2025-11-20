package com.example.pongservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PongController {

    @GetMapping("/api/data")
    public Map<String, String> getMessage() {
        // Visszaadunk egy egyszerű JSON választ
        return Map.of(
                "message", "Szia! En vagyok a Pong service a K8s clusterbol.",
                "host", System.getenv().getOrDefault("HOSTNAME", "localhost")
        );
    }
}