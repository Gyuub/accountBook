package com.gyub.accountbook.global.dto.account;

import com.gyub.accountbook.web.account.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDto {
    private Long id;
    private String name;

    public Account toEntity(){
        return Account.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
