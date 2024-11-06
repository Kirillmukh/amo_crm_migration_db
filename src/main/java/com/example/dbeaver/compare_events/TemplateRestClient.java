package com.example.dbeaver.compare_events;

import com.example.dbeaver.compare_events.entity.ResponseTag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class TemplateRestClient {
    private final RestClient restClient;
    public ResponseTag request(int page) {
        return restClient.get()
                .uri("companies/tags/?page={page}&limit=250", page)
                .retrieve()
                .body(ResponseTag.class);
    }

}
