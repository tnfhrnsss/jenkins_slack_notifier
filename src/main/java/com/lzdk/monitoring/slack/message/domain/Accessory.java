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
public class Accessory implements JsonSerializable {
    private String type;

    private PlainText text;

    private String url;

    public static Accessory create(String type, String label, String url) {
        return new Accessory(
            type,
            PlainText.create(TextType.PLAIN_TEXT, label),
            url
        );
    }

    @Override
    public String toString() {
        return toJson();
    }
}
