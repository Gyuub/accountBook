package com.gyub.accountbook.global.dto.account;


import com.gyub.accountbook.global.dto.authority.AuthorityDto;
import com.gyub.accountbook.global.dto.authority.AuthorityMemberDto;
import com.gyub.accountbook.web.account.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountAuthorityDto {

    private Long id;
    private String name;
    private List<AuthorityMemberDto> authoritys = new ArrayList<>();

    public static AccountAuthorityDto from(Account account) {
        return AccountAuthorityDto.builder()
                .id(account.getId())
                .name(account.getName())
                .authoritys(
                        account.getAccountAuthorities().stream()
                                .map(authority -> AuthorityMemberDto.from(authority))
                                .collect(Collectors.toList())
                )
                .build();
    }
}
