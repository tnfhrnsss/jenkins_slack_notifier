package com.lzdk.monitoring.jenkins.service;

import java.util.Map;

import com.lzdk.monitoring.git.domain.GitCommitHistory;
import com.lzdk.monitoring.git.servicie.GitLogFlowService;
import com.lzdk.monitoring.slack.message.service.SlackSendMessageService;
import com.lzdk.monitoring.slack.service.SlackUserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JenkinsAlarmFlowService {
    private final SlackSendMessageService slackSendMessageService;

    private final GitLogFlowService gitLogFlowService;

    private final SlackUserInfoService slackUserInfoService;

    @Async("taskExecutor")
    public void alert(String projectId) {
        try {
            sendMessage(gitLogFlowService.findLastLog(projectId));
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            //destroy();
        }
    }

    private void sendMessage(GitCommitHistory gitCommitHistory) {
        Map<String, String> slackUserProfiles = slackUserInfoService.findAll();

        try {
            slackUserProfiles
                .entrySet().forEach(k -> {
                    if (k.getKey().startsWith(gitCommitHistory.getAuthor())) {
                        log.debug("author info : {}", k.getValue());
                        slackSendMessageService.sendToAdmin(gitCommitHistory);
                        //slackSendMessageService.send(k.getValue(), gitCommitHistory);
                    } else {
                        slackSendMessageService.sendToAdmin(gitCommitHistory);
                    }
                });
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void destroy() {
      //  sonarQubeAuthorService.remove();
    }
}
