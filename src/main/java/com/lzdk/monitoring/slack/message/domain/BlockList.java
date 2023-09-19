package com.lzdk.monitoring.slack.message.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.lzdk.monitoring.utils.json.JsonSerializable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BlockList extends ArrayList<Block> implements JsonSerializable {
    public BlockList(@NotNull Collection<? extends Block> c) {
        super(c);
    }

    public static BlockList create(Block ...blocks) {
        BlockList list = new BlockList();
        list.addAll(Arrays.stream(blocks).toList());
        return list;
    }

    @Override
    public String toString() {
        return toJson();
    }
}
