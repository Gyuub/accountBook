package com.gyub.accountbook.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResultResponse<T> {
    private T data;
    private String message;
}