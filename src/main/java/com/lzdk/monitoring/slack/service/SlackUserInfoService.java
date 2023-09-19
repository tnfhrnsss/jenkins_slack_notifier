package com.lzdk.monitoring.slack.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.lzdk.monitoring.slack.channel.service.SlackConversationService;
import com.lzdk.monitoring.slack.utils.SlackProperties;
import com.slack.api.Slack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlackUserInfoService {
    private static final Map<String, String> profiles = new ConcurrentHashMap<>();

    private final SlackConversationService slackConversationService;

    private final SlackUserService slackUserService;

    static Map<String, String> findAll(List<String> members) {
        var client = Slack.getInstance().methods();

        members.forEach(user -> {
            try {
                var result =  client.usersInfo(r ->
                    r.token(SlackProperties.getToken()).user(user.trim())
                );

                String email = result.getUser().getProfile().getEmail();
                if (StringUtils.isNotEmpty(email)) {
                    profiles.put(email, user);
                }

            } catch (Exception e) {
                log.error(e.getMessage());
                throw new RuntimeException(e);
            }
        });

        return profiles;
    }

    public Map<String, String> findAll() {
        String channelId = slackConversationService.find();
        List<String> users = slackUserService.findAll(channelId);
        return findAll(users);
    }
}
