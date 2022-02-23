package com.gyub.accountbook.global.dto.member;

import com.gyub.accountbook.web.member.domain.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberRoleDto {
    private String name;

    public static MemberRoleDto from(MemberRole memberRole){
        return MemberRoleDto.builder()
                .name(memberRole.getAuthorityName())
                .build();
    }
}
