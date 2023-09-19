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
public class DmBlock implements JsonSerializable {
    private String type;

    private PlainText text;

    private Accessory accessory;

    public static DmBlock create(String message, String url) {
        return new DmBlock(
            "section",
            PlainText.create(TextType.MRKDWN, message),
            Accessory.create(url)
        );
    }

    @Override
    public String toString() {
        return toJson();
    }
}
