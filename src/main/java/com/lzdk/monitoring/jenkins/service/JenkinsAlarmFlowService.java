package com.lzdk.monitoring.jenkins.service;

import com.lzdk.monitoring.git.domain.GitCommitHistory;
import com.lzdk.monitoring.git.servicie.GitLogFlowService;
import com.lzdk.monitoring.jenkins.domain.JenkinsMonitorSdo;
import com.lzdk.monitoring.slack.message.domain.SlackMessageCdo;
import com.lzdk.monitoring.slack.message.service.SlackSendMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JenkinsAlarmFlowService {
    private final SlackSendMessageService slackSendMessageService;

    private final GitLogFlowService gitLogFlowService;

    @Async("taskExecutor")
    public void alert(String projectId, JenkinsMonitorSdo jenkinsMonitorSdo) {
        try {
            GitCommitHistory gitCommitHistory = gitLogFlowService.findLastLog(projectId, jenkinsMonitorSdo.getBranch());
            if (gitCommitHistory != null) {
                sendMessage(SlackMessageCdo.create(gitCommitHistory.getAuthor().getEmailAddress(), StringUtils.join("*[", jenkinsMonitorSdo.getJobId(), "]* ", projectId)));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private void sendMessage(SlackMessageCdo slackMessageCdo) {
        slackSendMessageService.message(slackMessageCdo);
    }
}
