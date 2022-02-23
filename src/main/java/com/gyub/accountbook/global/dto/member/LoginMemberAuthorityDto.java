package com.gyub.accountbook.global.dto.member;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginMemberAuthorityDto {
    private String email;
    private String nickname;
    private String password;
}
