package com.lzdk.monitoring.slack.message.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TextType {
    MRKDWN,
    PLAIN_TEXT;
}
