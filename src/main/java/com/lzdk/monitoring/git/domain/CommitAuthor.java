package com.lzdk.monitoring.git.domain;

import com.lzdk.monitoring.utils.json.JsonSerializable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommitAuthor implements JsonSerializable {
    private String name;

    private String emailAddress;

    public static CommitAuthor create(String name, String email) {
        return new CommitAuthor(
            name,
            email
        );
    }

    @Override
    public String toString() {
        return toJson();
    }
}
