package com.gyub.accountbook.global.dto.account;


import com.gyub.accountbook.web.account.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private Long id;
    private String name;

    public static AccountDto from(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .name(account.getName())
                .build();
    }
}
