package com.gyub.accountbook.global.dto.member;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginMemberDto {
    private String email;
    private String password;
}
