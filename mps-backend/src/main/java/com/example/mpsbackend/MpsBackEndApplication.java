package com.example.mpsbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.controller", "com.example.service", "com.example.serviceImpl" })
@EntityScan(basePackages = { "com.example.entity" })
@EnableJpaRepositories(basePackages = { "com.example.repository" })
public class MpsBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(MpsBackEndApplication.class, args);
        System.out.println("start server");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
