package com.example.dbeaver.compare_events;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class Config {
    @Bean
    public TemplateRestClient templateRestClient(
            @Value("${baseUrl}") String baseUrl,
            @Value("${token}") String token) {
        RestClient client = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("authorization", "Bearer " + token)
                .build();
        return new TemplateRestClient(client);
    }
}
