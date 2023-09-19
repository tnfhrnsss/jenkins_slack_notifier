package com.lzdk.monitoring.slack.utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SlackApiConfig {
    private static String token = "";

    @Value("${monitoring.slack.token:}")
    public void setToken(String value) {
        token = value;
    }

    public static String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "SlackConfig{"
            + "token="
            + token
            + '}';
    }
}
