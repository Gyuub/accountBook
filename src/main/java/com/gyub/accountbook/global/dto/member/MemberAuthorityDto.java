package com.gyub.accountbook.global.dto.member;

import com.gyub.accountbook.web.member.domain.MemberAuthority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberAuthorityDto {
    private Long id;
    private MemberRoleDto memberRoleDto;

    public static MemberAuthorityDto from(MemberAuthority memberAuthority){
        return MemberAuthorityDto.builder()
                .id(memberAuthority.getId())
                .memberRoleDto(MemberRoleDto.from(memberAuthority.getMemberRole()))
                .build();
    }
}
