package com.lzdk.monitoring.jenkins.domain;

import com.lzdk.monitoring.utils.json.JsonSerializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JenkinsMonitorSdo implements JsonSerializable {
    private String jobId;

    private String branch;

    @Override
    public String toString() {
        return toJson();
    }
}
