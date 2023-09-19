package com.lzdk.monitoring.jenkins.controller;

import com.lzdk.monitoring.jenkins.service.JenkinsAlarmFlowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("jenkins")
public class JenkinsMonitorController {
    private final JenkinsAlarmFlowService jenkinsAlarmFlowService;

    /**
     *
     *                 def apiUrl = 'http://172.16.120.206:7777/jenkins/projects/${repo_name}/alarm'
     *                 def response = sh(script: "curl -X POST $apiUrl", returnStatus: true)
     *                 if (response != 0) {
     *                     error "API 호출이 실패했습니다."
     *                 }
     * @param projectId
     */
    @PostMapping("projects/{projectId}/alarm")
    public void alert(@PathVariable String projectId) {
        jenkinsAlarmFlowService.alert(projectId);
    }
}
