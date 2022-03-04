package com.gyub.accountbook.global.dto.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gyub.accountbook.global.dto.authority.AuthorityAccountDto;
import com.gyub.accountbook.web.member.domain.Member;
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
public class MemberAccountDto {
    @JsonIgnore
    private Long id;

    private String email;
    private String nickname;
    private List<AuthorityAccountDto> authoritys = new ArrayList<>();

    public static MemberAccountDto from(Member member){
        if(member == null) return null;

        return MemberAccountDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .authoritys(
                        member.getAccountAuthorities().stream()
                                .map(authority -> AuthorityAccountDto.from(authority))
                                .collect(Collectors.toList())
                )
                .build();
    }
}
