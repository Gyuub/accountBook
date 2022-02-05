package com.gyub.accountbook.global.dto.account;

import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.account.domain.detail.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailDto {

    private Long id;

    private String title;
    private String contents;
    private Integer amount;
    private String writer;

    private Long accountId;

    private Long categoryId;

}
