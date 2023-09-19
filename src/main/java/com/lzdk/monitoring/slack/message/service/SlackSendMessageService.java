package com.lzdk.monitoring.slack.message.service;

import java.util.Map;

import com.lzdk.monitoring.slack.message.domain.SlackMessageCdo;
import com.lzdk.monitoring.slack.service.SlackUserInfoService;
import com.lzdk.monitoring.slack.utils.SlackProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlackSendMessageService {
    @Value("${monitoring.slack.admin.id:}")
    private String adminId;

    private final SlackMessageService slackMessageService;

    private final SlackBlockService slackBlockService;

    private final SlackUserInfoService slackUserInfoService;

    private void sendToAdmin(String message) {
        if (StringUtils.isEmpty(adminId)) {
            log.debug("Author not found in the Slack channel. : {} ", message);
        } else {
            slackMessageService.publish(adminId, slackBlockService.makeDmBlock(message));
        }
    }

    public void message(SlackMessageCdo slackMessageCdo) {
        Map<String, String> slackUserProfiles = slackUserInfoService.findAll();

        try {
            if (slackUserProfiles.containsKey(slackMessageCdo.getTo())) {
                String userId = slackUserProfiles.get(slackMessageCdo.getTo());
                slackMessageService.publish(SlackProperties.getChannelId(), slackBlockService.makeChannelBlocks(userId, slackMessageCdo.getMessage()));
            } else {
                sendToAdmin(slackMessageCdo.getMessage());
            };
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
