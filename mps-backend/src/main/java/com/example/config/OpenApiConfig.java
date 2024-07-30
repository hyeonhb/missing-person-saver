package com.example.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Getter
@Configuration
public class OpenApiConfig {
    @Value("${safe.api.key}")
    private String apiKey;

    @Value("${safe.api.id}")
    private String apiId;

    @Value("${openai.url.model}")
    private String modelUrl;

    @Value("${openai.url.model-list}")
    private String modelListUrl;

    @Value("${openai.model}")
    private String gptModel;

    @Value("${openai.api.key}")
    private String gptKey;

    @Bean
    public HttpHeaders httpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + getGptKey());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
