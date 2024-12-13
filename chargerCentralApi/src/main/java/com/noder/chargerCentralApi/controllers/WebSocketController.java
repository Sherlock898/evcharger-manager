package com.noder.chargerCentralApi.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class WebSocketController {
    private final RestTemplate restTemplate;

    public WebSocketController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
    
    @MessageMapping("/sendMessage")
    public void handleMessage(String message) {
        String apiUrl = "http://localhost:8080/api/transactions";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl).queryParam("message", message);

        // Make the HTTP request
        String response = restTemplate.postForObject(uriBuilder.toUriString(), null, String.class);
        System.out.println("API response: " + response);
    }
}
