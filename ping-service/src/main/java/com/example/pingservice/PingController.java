package com.example.pingservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PingController {

    // Default értékkel védve, ha hiányozna a config
    @Value("${pong.service.url:http://localhost:8081/api/data}")
    private String pongServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ping")
    public String pingPong() {
        String pongResponse;
        String cardColor = "#e8f5e9";
        String statusIcon = "✅";

        try {
            pongResponse = restTemplate.getForObject(pongServiceUrl, String.class);
        } catch (Exception e) {
            pongResponse = "Hiba: Nem érem el a Pong service-t! (" + e.getMessage() + ")";
            cardColor = "#ffebee";
            statusIcon = "❌";
        }

        return """
            <html>
            <head>
                <title>K8s Ping-Pong</title>
                <style>
                    body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #2d3436; color: white; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }
                    /* FIGYELEM: Itt a 100%%-ot duplán kell írni a Java miatt! */
                    .card { background-color: white; color: #333; padding: 30px; border-radius: 15px; box-shadow: 0 10px 25px rgba(0,0,0,0.5); text-align: center; max-width: 500px; width: 100%%; }
                    .response-box { background-color: %s; padding: 15px; border-radius: 8px; margin: 20px 0; border: 1px solid #ccc; font-family: monospace; }
                    button { background-color: #0984e3; color: white; border: none; padding: 10px 20px; font-size: 16px; border-radius: 5px; cursor: pointer; transition: 0.3s; }
                    button:hover { background-color: #74b9ff; }
                </style>
            </head>
            <body>
                <div class="card">
                    <h1>%s Ping Service</h1>
                    <p>Kérés küldése a Pong service felé...</p>
                    
                    <div class="response-box">
                        <strong>Válasz:</strong><br>
                        %s
                    </div>
                    
                    <button onclick="window.location.reload()">🔄 Újra küldés</button>
                    <p style="font-size: 12px; color: #777; margin-top: 20px;">Host: %s</p>
                </div>
            </body>
            </html>
            """.formatted(cardColor, statusIcon, pongResponse, System.getenv().getOrDefault("HOSTNAME", "localhost"));
    }
}