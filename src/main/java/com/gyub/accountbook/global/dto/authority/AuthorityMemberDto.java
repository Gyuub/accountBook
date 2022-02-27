package com.gyub.accountbook.global.dto.authority;

import com.gyub.accountbook.global.dto.account.AccountDto;
import com.gyub.accountbook.global.dto.member.MemberDto;
import com.gyub.accountbook.web.authority.domain.Authority;
import com.gyub.accountbook.web.authority.domain.Role;
import com.gyub.accountbook.web.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityMemberDto {

    private Long id;
    private MemberDto member;
    private Role role;


    public static AuthorityMemberDto from(Authority authority){
        return AuthorityMemberDto.builder()
                .id(authority.getId())
                .member(MemberDto.from(authority.getMember()))
                .role(authority.getRole())
                .build();
    }
}
