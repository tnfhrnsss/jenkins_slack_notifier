package com.lzdk.monitoring.slack.message.service;

import com.lzdk.monitoring.slack.message.domain.BlockList;
import com.lzdk.monitoring.slack.message.domain.DmBlock;
import com.lzdk.monitoring.slack.message.domain.HeaderBlock;
import com.lzdk.monitoring.slack.message.domain.MarkdownBlock;
import com.lzdk.monitoring.slack.utils.SlackProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlackBlockService {
    @Value("${monitoring.jenkins.consoleUrl:}")
    private String consoleUrl;

    public String makeDmBlock(String componentKey) {
        BlockList blockList = BlockList.addHeader(HeaderBlock.create(SlackProperties.getDeliveryMessage()));
        blockList.addDmBlock(
            DmBlock.create(componentKey, consoleUrl)
        );
        return blockList.toJson();
    }

    public String makeChannelBlocks(String mentionTo, String message) {
        BlockList blockList = BlockList.addHeader(HeaderBlock.create(SlackProperties.getDeliveryMessage()));
        String blockMessage = message;
        if (StringUtils.isNotEmpty(mentionTo)) {
            blockMessage = StringUtils.join("<@" + mentionTo + "> \n", message);
        }
        if (StringUtils.isEmpty(consoleUrl)) {
            blockList.addMentionBlock(MarkdownBlock.create(blockMessage));
        } else {
            blockList.addDmBlock(DmBlock.create(blockMessage, consoleUrl));
        }
        return blockList.toJson();
    }
}
