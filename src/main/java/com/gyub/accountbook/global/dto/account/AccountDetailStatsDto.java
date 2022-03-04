package com.gyub.accountbook.global.dto.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gyub.accountbook.web.account.domain.AccountDetail;
import com.gyub.accountbook.web.account.domain.AccountDetailRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailStatsDto {

    private String categoryName;
    private Integer amount;

}
