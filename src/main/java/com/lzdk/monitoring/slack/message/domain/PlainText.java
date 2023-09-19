package com.lzdk.monitoring.slack.message.domain;

import com.lzdk.monitoring.slack.message.domain.enums.TextType;
import com.lzdk.monitoring.utils.json.JsonSerializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PlainText implements JsonSerializable {
    private String type;

    private String text;

    @Override
    public String toString() {
        return toJson();
    }

    public static PlainText create(TextType textType, String message) {
        return new PlainText(textType.name().toLowerCase(), message);
    }
}
