package com.lzdk.monitoring.slack.message.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.lzdk.monitoring.utils.json.JsonSerializable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FieldList extends ArrayList<PlainText> implements JsonSerializable {
    public FieldList(@NotNull Collection<? extends PlainText> c) {
        super(c);
    }

    public static FieldList create(PlainText ...plainTexts) {
        FieldList list = new FieldList();
        list.addAll(Arrays.stream(plainTexts).toList());
        return list;
    }

    public static FieldList create(List<PlainText> plainTexts) {
        return new FieldList(plainTexts);
    }

    @Override
    public String toString() {
        return toJson();
    }
}
