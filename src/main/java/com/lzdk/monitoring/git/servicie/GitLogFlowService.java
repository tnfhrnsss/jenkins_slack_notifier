package com.lzdk.monitoring.git.servicie;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import com.lzdk.monitoring.git.domain.GitCommitHistory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GitLogFlowService {
    @Value("${monitoring.git.repository.path:}")
    private String repositoryPath;

    @Value("${monitoring.git.branch:develop}")
    private String branch;

    public void findGitLog(String projectId) {
        log.debug("failed projectId : {}", projectId);

        findLastLog(projectId);
//
//        File localPath = new File("/path/to/your/local/repository");
//
//        try (Repository repository = new RepositoryBuilder().setGitDir(localPath).build()) {
//            // 리모트 브랜치 가져오기 (예: origin/master)
//            Ref remoteBranchRef = repository.findRef("refs/remotes/origin/master");
//
//            if (remoteBranchRef != null) {
//                ObjectId objectId = remoteBranchRef.getObjectId();
//                try (RevWalk revWalk = new RevWalk(repository)) {
//                    RevCommit commit = null;
//                    try {
//                        commit = revWalk.parseCommit(objectId);
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                    System.out.println("Last Pushed Commit Hash: " + commit.getName());
//                    System.out.println("Author: " + commit.getAuthorIdent().getName());
//                    System.out.println("Date: " + commit.getAuthorIdent().getWhen());
//                    System.out.println("Message: " + commit.getFullMessage());
//                }
//            } else {
//                System.err.println("Remote branch not found.");
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
    }

    public GitCommitHistory findLastLog(String projectId) {
        GitCommitHistory history = null;
        File localPath = new File(repositoryPath + projectId + "_develop/.git");

        try (Repository repository = new RepositoryBuilder().setGitDir(localPath).build()) {
            // 리모트 브랜치 가져오기 (예: origin/master)

            Repository existingRepo = new FileRepositoryBuilder()
                .setGitDir(localPath)
                .build();

            Ref master = existingRepo.findRef(branch);
//ERROR: !!!!!!!!!!!!!!! talk-api-support/develop build failed.
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

                    history = GitCommitHistory.create(
                        projectId,
                        commit.getName(),
                        commit.getAuthorIdent().getName(),
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

    public static void main(String[] args) throws IOException {


// 원격 Git 리포지토리의 URL
     /*   URI remoteUri = null;
        try {
            remoteUri = new URI("https://gitlab.spectra.co.kr/attic-project/talk/talk-api-messengerconnector.git");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // 로컬 Git 리포지토리 경로 (임시로 사용)
        Repository localRepository = new RepositoryBuilder().setWorkTree(new File("/Users/jhsim/Documents/repository")).build();

        CredentialsProvider cp = new UsernamePasswordCredentialsProvider("jhsim", "47wpdlwpdl!");

        // 원격 리포지토리 클론
        try (Git git = Git.cloneRepository()
                .setURI(remoteUri.toString())
                .setCredentialsProvider(cp)
                .setDirectory(localRepository.getDirectory().getParentFile())
                .call()) {

            // 리모트 브랜치 가져오기 (예: origin/master)
            Ref remoteBranchRef = git.getRepository().findRef("refs/remotes/origin/develop");

            if (remoteBranchRef != null) {
                ObjectId objectId = remoteBranchRef.getObjectId();
                try (RevWalk revWalk = new RevWalk(git.getRepository())) {
                    RevCommit commit = revWalk.parseCommit(objectId);
                    System.out.println("Last Pushed Commit Hash: " + commit.getName());
                    System.out.println("Author: " + commit.getAuthorIdent().getName());
                    System.out.println("Date: " + commit.getAuthorIdent().getWhen());
                    System.out.println("Message: " + commit.getFullMessage());
                }
            } else {
                System.err.println("Remote branch not found.");
            }
        } catch (IncorrectObjectTypeException e) {
            throw new RuntimeException(e);
        } catch (MissingObjectException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidRemoteException e) {
            throw new RuntimeException(e);
        } catch (TransportException e) {
            throw new RuntimeException(e);
        } catch (GitAPIException e) {
            throw new RuntimeException(e);
        } finally {
            // 임시 로컬 리포지토리 정리
            if (localRepository != null) {
                localRepository.close();
            }
        }*/

    }
}
