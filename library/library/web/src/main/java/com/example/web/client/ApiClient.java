package com.example.web.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiClient {

    private final RestTemplate restTemplate;

    public ApiClient() {
        this.restTemplate = new RestTemplate();
    }

    public String fetchDataFromApi(String url) {
        return restTemplate.getForObject(url, String.class);
    }
}
