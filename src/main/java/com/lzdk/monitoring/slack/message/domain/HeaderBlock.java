package com.lzdk.monitoring.slack.message.domain;

import com.lzdk.monitoring.slack.message.domain.enums.TextType;
import com.lzdk.monitoring.utils.json.JsonSerializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HeaderBlock implements JsonSerializable {
    private String type;

    private PlainText text;

    public static HeaderBlock create(String message) {
        return new HeaderBlock(
            "header",
            PlainText.create(TextType.PLAIN_TEXT, message)
        );
    }

    @Override
    public String toString() {
        return toJson();
    }
}
