package com.lzdk.monitoring.jenkins.controller;

import com.lzdk.monitoring.jenkins.domain.JenkinsMonitorSdo;
import com.lzdk.monitoring.jenkins.service.JenkinsAlarmFlowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("jenkins")
public class JenkinsMonitorController {
    private final JenkinsAlarmFlowService jenkinsAlarmFlowService;

    @PostMapping("projects/{projectId}/alarm")
    public void alert(
        @PathVariable String projectId,
        @RequestBody JenkinsMonitorSdo jenkinsMonitorSdo
    ) {
        jenkinsAlarmFlowService.alert(projectId, jenkinsMonitorSdo);
    }
}
