package com.gyub.accountbook.global.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class ModifyMemberDto {
    private Long id;
    private String nickname;
    private String password;

}
