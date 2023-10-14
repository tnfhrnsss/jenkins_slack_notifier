# When jenkins builds fail, Send message to slack channel with commit author mention.

It is an application that sends notifications to Slack when Jenkins builds fail.

## Requirements

* This project requires Java 8 or later.
* spring boot version 3.x
* slack-api-client 1.29.2

# Usage

* You can configure the git repository path and Slack details in the application.yml file.
  * You only need to set either the Channel ID or the Channel Name.
     ```
      monitoring:
        git:
          repository.path:
        jenkins:
          consoleUrl:
        slack:
          token: xoxb-xxxxxxxxxxxxxxxxxxxxxxxxxxxx
          delivery:
            message: "Jenkins Build Fail"
          channel:
            id:
            name:
      ```
* When a matching user does not exist in the Slack channel, it can be configured to send a direct message to the admin.
  * default matching with commit author email address and slack email address.
  * run application
      * build this project and run JenkinsMonitoringApplication.class
      * [OR] java -jar jenkins_slack_notifier*.jar
      * execute api
        ```
          curl -X POST 'http://localhost:7777/jenkins/projects/{projectId}/{branch}/alarm'
        ```

## output snapshot

### blog reference


