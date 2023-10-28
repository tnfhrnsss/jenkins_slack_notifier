package com.lzdk.monitoring.slack.message.service;

import java.util.Map;

import com.lzdk.monitoring.slack.message.domain.SlackMessageCdo;
import com.lzdk.monitoring.slack.service.SlackUserInfoService;
import com.lzdk.monitoring.slack.utils.SlackProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlackSendMessageService {
    private final SlackMessageService slackMessageService;

    private final SlackBlockService slackBlockService;

    private final SlackUserInfoService slackUserInfoService;

    public void message(SlackMessageCdo slackMessageCdo) {
        Map<String, String> slackUserProfiles = slackUserInfoService.findAll();

        try {
            if (slackUserProfiles.containsKey(slackMessageCdo.getTo())) {
                String userId = slackUserProfiles.get(slackMessageCdo.getTo());
                slackMessageService.publish(SlackProperties.getChannelId(), slackBlockService.makeChannelBlocks(userId, slackMessageCdo.getMessage()));
            } else {
                slackMessageService.publish(SlackProperties.getChannelId(), slackBlockService.makeChannelBlocks("", slackMessageCdo.getMessage()));
            };
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
