package com.gyub.accountbook.global.exception.custom;

import com.gyub.accountbook.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class MemberNotFoundException extends RuntimeException {
    private ErrorCode errorCode;

    public MemberNotFoundException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
