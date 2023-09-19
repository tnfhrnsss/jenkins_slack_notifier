package com.lzdk.monitoring.slack.service;

import java.util.List;

import com.lzdk.monitoring.slack.utils.SlackProperties;
import com.slack.api.Slack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlackUserService {

    public List findAll(String channelId) {
        var client = Slack.getInstance().methods();

        try {
            var result =  client.conversationsMembers(r ->
                r.token(SlackProperties.getToken()).channel(channelId)
            );
            return result.getMembers();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
