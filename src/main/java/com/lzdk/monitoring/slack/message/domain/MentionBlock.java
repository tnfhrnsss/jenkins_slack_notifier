package com.lzdk.monitoring.slack.message.domain;

import java.util.Map;
import java.util.stream.Collectors;

import com.lzdk.monitoring.slack.message.domain.enums.TextType;
import com.lzdk.monitoring.utils.json.JsonSerializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MentionBlock implements JsonSerializable {
    private String type;

    private PlainText text;

    private Accessory accessory;

    private FieldList fields;

    public static MentionBlock create(Map<String, Map> message, String url) {
        return new MentionBlock(
            "section",
            PlainText.create(TextType.MRKDWN, "Please check your code."),
            Accessory.create(url),
            FieldList.create(
                message.entrySet()
                    .stream()
                    .map(k -> new PlainText(TextType.PLAIN_TEXT.name().toLowerCase(), "<@" + k.getKey() + "> " + k.getValue().keySet()))
                    .collect(Collectors.toList())
            )
        );
    }

    @Override
    public String toString() {
        return toJson();
    }
}
