package com.example.jsimplerest.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class ExtraRestClient {

    @Bean
    public RestTemplate restClient(RestTemplateBuilder builder){

        builder
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                .setConnectTimeout(Duration.ofSeconds(60))
                .setReadTimeout(Duration.ofSeconds(120));

        return builder.build();
    }
}
