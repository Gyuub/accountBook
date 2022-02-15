package com.gyub.accountbook.global.dto.member;

import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.member.domain.MemberAuthority;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private String email;
    private String nickname;
    private Set<MemberAuthority> authorities = new HashSet<>();

    public static MemberDto from(Member member){
        if(member == null) return null;

        return MemberDto.builder()
                .email(member.getEmail())
                .nickname(member.getNickname())
                .authorities(member.getAuthorities())
                .build();
    }
}
