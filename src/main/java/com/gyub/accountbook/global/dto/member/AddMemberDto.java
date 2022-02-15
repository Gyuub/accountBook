package com.gyub.accountbook.global.dto.member;

import com.gyub.accountbook.web.member.domain.Member;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddMemberDto {

    private String email;
    private String nickname;
    private String password;


    public Member toEntity(){
        return Member.builder()
                .email(this.email)
                .nickname(this.nickname)
                .password(this.password)
                .build();
    }



}
