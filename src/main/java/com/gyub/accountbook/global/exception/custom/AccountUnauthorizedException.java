package com.gyub.accountbook.global.exception.custom;

import com.gyub.accountbook.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class AccountUnauthorizedException extends RuntimeException {
    private ErrorCode errorCode;

    public AccountUnauthorizedException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
