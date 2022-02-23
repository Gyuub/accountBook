package com.gyub.accountbook.global.exception.custom;

import com.gyub.accountbook.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidValueException extends RuntimeException{
    private ErrorCode errorCode;

    public InvalidValueException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}