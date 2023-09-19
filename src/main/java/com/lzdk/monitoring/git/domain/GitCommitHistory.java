package com.lzdk.monitoring.git.domain;

import java.util.Date;

import com.lzdk.monitoring.utils.json.JsonSerializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GitCommitHistory implements JsonSerializable {
    private String projectId;

    private String hashId;

    private String author;

    private String date;

    private String message;

    public static GitCommitHistory create(String projectId, String hashId, String author, Date when, String fullMessage) {
        return new GitCommitHistory(
            projectId,
            hashId,
            author,
            when.toString(),
            fullMessage
        );
    }

    @Override
    public String toString() {
        return toJson();
    }
}
