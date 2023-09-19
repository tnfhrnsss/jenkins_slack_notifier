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
public class MarkdownBlock implements JsonSerializable {
    private String type;

    private PlainText text;

    public static MarkdownBlock create(String message) {
        return new MarkdownBlock(
            "section",
            PlainText.create(TextType.MRKDWN, message)
        );
    }

    @Override
    public String toString() {
        return toJson();
    }
}
