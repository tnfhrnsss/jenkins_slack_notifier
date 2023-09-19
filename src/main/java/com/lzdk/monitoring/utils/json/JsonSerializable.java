package com.lzdk.monitoring.utils.json;

import java.io.Serializable;

public interface JsonSerializable extends Serializable {
    default String toJson() {
        return JsonParser.toJson(this);
    }

    default String toJsonWithoutPretty() {
        return JsonParser.toJson(this);
    }
}
