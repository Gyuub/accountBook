package com.gyub.accountbook.web.account.domain;

import com.gyub.accountbook.global.util.Constant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountDetailRole implements Constant {
    INCOME("AC01"),
    OUTCOME("AC02");

    private final String code;

}
