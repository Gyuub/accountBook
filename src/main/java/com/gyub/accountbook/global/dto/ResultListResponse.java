package com.gyub.accountbook.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class ResultListResponse<T> {
    private T data;
    private int count;
}
