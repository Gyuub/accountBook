package com.gyub.accountbook.global.dto.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.member.domain.MemberAuthority;
import com.gyub.accountbook.web.member.domain.MemberRole;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    @JsonIgnore
    private Long id;

    private String email;
    private String nickname;


    public static MemberDto from(Member member){
        if(member == null) return null;

        return MemberDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())

                .build();
    }
}
