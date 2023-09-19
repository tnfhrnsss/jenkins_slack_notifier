package com.lzdk.monitoring.slack.message.service;

import java.util.Map;
import java.util.stream.Collectors;

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
        blockList.addDmBlock(DmBlock.create(StringUtils.join("<@" + mentionTo + "> ", message), consoleUrl));
        return blockList.toJson();
    }
}
