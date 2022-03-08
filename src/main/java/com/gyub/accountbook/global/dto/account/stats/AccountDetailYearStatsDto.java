package com.gyub.accountbook.global.dto.account.stats;

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
public class AccountDetailYearStatsDto {

    private String writeDate;
    private Integer incomeAmount;
    private Integer outcomeAmount;


}
