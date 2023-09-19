package com.lzdk.monitoring.utils.feign;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return (requestTemplate) -> {
            Map<String, Collection<String>> headers = new LinkedHashMap();
            headers.put("Accept", Collections.singletonList("application/json"));
            headers.put("Content-Type", Arrays.asList("application/json", "charset=utf8"));
            requestTemplate.headers(headers);
        };
    }
}
