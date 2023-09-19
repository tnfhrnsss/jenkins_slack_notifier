package com.lzdk.monitoring.slack.message.domain;

import com.lzdk.monitoring.utils.json.JsonSerializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SlackMessageCdo implements JsonSerializable {
    private String to;

    private String message;

    @Override
    public String toString() {
        return toJson();
    }

    public static SlackMessageCdo create(String email, String message) {
        return new SlackMessageCdo(email, message);
    }
}
