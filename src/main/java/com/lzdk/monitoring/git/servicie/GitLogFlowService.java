package com.lzdk.monitoring.git.servicie;

import java.io.File;
import java.io.IOException;

import com.lzdk.monitoring.git.domain.GitCommitHistory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GitLogFlowService {
    @Value("${monitoring.git.repository.path:}")
    private String repositoryPath;

    public GitCommitHistory findLastLog(String projectId, String branch) {
        GitCommitHistory history = null;
        File localPath = new File(StringUtils.join(repositoryPath, "/", projectId, "/.git"));

        try (Repository repository = new RepositoryBuilder().setGitDir(localPath).build()) {
            Repository existingRepo = new FileRepositoryBuilder()
                .setGitDir(localPath)
                .build();

            Ref master = existingRepo.findRef(branch);
            if (master != null) {
                ObjectId objectId = master.getObjectId();
                try (RevWalk revWalk = new RevWalk(repository)) {
                    RevCommit commit = null;
                    try {
                        commit = revWalk.parseCommit(objectId);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    log.debug("Last Pushed Commit Hash: " + commit.getName());
                    log.debug("Author: " + commit.getAuthorIdent().getName());
                    log.debug("Date: " + commit.getAuthorIdent().getWhen());
                    log.debug("Message: " + commit.getFullMessage());
                    log.debug("Email:" + commit.getAuthorIdent().getEmailAddress());

                    history = GitCommitHistory.create(
                        projectId,
                        commit.getName(),
                        commit.getAuthorIdent().getName(),
                        commit.getAuthorIdent().getEmailAddress(),
                        commit.getAuthorIdent().getWhen(),
                        commit.getFullMessage()
                    );
                }
            } else {
                log.error("Remote branch not found.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return history;
    }
}
