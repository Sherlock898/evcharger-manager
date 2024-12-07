package com.noder.chargerCentralApi.controllers;

import org.springframework.stereotype.Controller;
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

    @Message

}
