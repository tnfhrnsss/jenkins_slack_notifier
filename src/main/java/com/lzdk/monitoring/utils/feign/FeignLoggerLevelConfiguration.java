package com.lzdk.monitoring.utils.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class FeignLoggerLevelConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
