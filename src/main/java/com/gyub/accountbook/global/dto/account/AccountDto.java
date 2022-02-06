package com.gyub.accountbook.global.dto.account;


import com.gyub.accountbook.web.account.domain.detail.AccountDetail;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AccountDto {

    private Long id;
    private String name;
    private List<AccountDetail> detailList = new ArrayList<>();

}
