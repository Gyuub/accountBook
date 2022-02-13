package com.gyub.accountbook.global.dto.member;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private String email;
    private String password;
    private String nickname;
}
