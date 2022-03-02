package com.gyub.accountbook.global.dto.authority;

import com.gyub.accountbook.global.dto.account.AccountDto;
import com.gyub.accountbook.global.dto.member.MemberDto;
import com.gyub.accountbook.web.authority.domain.Authority;
import com.gyub.accountbook.web.authority.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityAccountDto {

    private Long id;
    private AccountDto account;
    private Role role;


    public static AuthorityAccountDto from(Authority authority){
        return AuthorityAccountDto.builder()
                .id(authority.getId())
                .account(AccountDto.from(authority.getAccount()))
                .role(authority.getRole())
                .build();
    }
}
