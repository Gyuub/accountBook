package com.gyub.accountbook.global.util;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum type deSerialized
 */
public interface Constant {
    @JsonValue
    String getCode();
}