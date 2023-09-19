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
public class Block implements JsonSerializable {
    private String type;

    private PlainText text;

    private Accessory accessory;

    public static Block createMarkdown(String message, String url) {
        return new Block(
            "section",
            PlainText.create(TextType.MRKDWN, message),
            Accessory.create("button", "Click Me", url)
        );
    }

    @Override
    public String toString() {
        return toJson();
    }
}
