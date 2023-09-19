package com.lzdk.monitoring.utils.feign;

import feign.Retryer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class FeignRetryConfiguration {
    @Bean
    public Retryer retryer(@Value("${monitoring.feign.retryPeriod:0}") final int retryPeriod,
                       @Value("${monitoring.feign.retryMaxPeriod:0}") final int retryMaxPeriod,
                       @Value("${monitoring.feign.retryMaxAttempts:0}") final int retryMaxAttempts) {
        return new Retryer.Default(retryPeriod, retryMaxPeriod, retryMaxAttempts);
    }
}
