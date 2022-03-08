package com.gyub.accountbook.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND(10404,"COMMON-ERR-001","PAGE NOT FOUND"),
    INTER_SERVER_ERROR(10500,"COMMON-ERR-002","INTER SERVER ERROR"),
    INVALID_VALUE(10400,"COMMON-ERR-003","INVALID VALUE ERROR"),

    //**유저**//
    EMAIL_DUPLICATION(10400,"MEMBER-ERR-001","EMAIL DUPLICATED"),
    MEMBER_NOT_FOUND(10400,"MEMBER-ERR-002","MEMBER_NOT_FOUND"),


    //**가계부**//
    ACCOUNT_ACCESS_DENIED(10400,"ACCOUT-ERR-001","ACCOUNT ACCESS DENIED"),
    ACCOUNT_UNAUTHORIZED(10401,"ACCOUT-ERR-002","ACCOUNT UNAUTHORIZED "),
    ;

    private int status;
    private String errorCode;
    private String message;
}
