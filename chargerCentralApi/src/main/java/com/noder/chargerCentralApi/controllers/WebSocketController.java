package com.noder.chargerCentralApi.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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
    @SendTo("/topic/response")
    public String handleMessage(String message) {
        String apiUrl = "http://localhost:8080/api/transactions";

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("message", message);

        try {
            String response = restTemplate.postForObject(apiUrl, requestBody, String.class);
            System.out.println("API response: " + response);
            return response;
        } catch (HttpClientErrorException e) {
            System.err.println("Error while sending message to API: " + e.getMessage());
            return "Error: Unable to process the request.";
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            return "Error: Unexpected server issue.";
        }
    }
}
