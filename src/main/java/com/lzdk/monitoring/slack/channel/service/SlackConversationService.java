package com.lzdk.monitoring.slack.channel.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.lzdk.monitoring.slack.utils.SlackApiConfig;
import com.slack.api.Slack;
import com.slack.api.methods.SlackApiException;
import com.slack.api.model.Conversation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SlackConversationService {
    private static String channelId;

    private static String channelName;

    private static final Map<String, String> channelList = new ConcurrentHashMap<>();

    @Value("${monitoring.slack.channel.name:}")
    public void setChannelName(String value) {
        channelName = value;
    }

    @Value("${monitoring.slack.channel.id:}")
    public void setChannelId(String value) {
        channelId = value;
    }

    static String findConversationId() {
        var client = Slack.getInstance().methods();
        var conversationId = "";
        try {
            var result = client.conversationsList(r -> r.token(SlackApiConfig.getToken()));
            for (Conversation channel : result.getChannels()) {
                if (channel.getName().equals(channelName)) {
                    conversationId = channel.getId();
                    log.info("Found conversation ID: {}", conversationId);
                    break;
                }
            }
        } catch (IOException | SlackApiException e) {
            log.error("error: {}", e.getMessage(), e);
        }

        if (StringUtils.isNotEmpty(conversationId)) {
            channelList.put(channelName, conversationId);
        }
        return conversationId;
    }

    public String find() {
        if (StringUtils.isNotEmpty(channelId)) {
            return channelId;
        }

        if (channelList.containsKey(channelName)) {
            return channelList.get(channelName);
        }
        return findConversationId();
    }
}
