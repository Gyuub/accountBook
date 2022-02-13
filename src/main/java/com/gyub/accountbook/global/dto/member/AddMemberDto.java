package com.gyub.accountbook.global.dto.member;

import com.gyub.accountbook.web.member.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMemberDto {

    private Long id;
    private String email;
    private String nickname;
    private String password;

    public AddMemberDto(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public Member toEntity(){
        return Member.builder()
                .email(this.email)
                .nickname(this.nickname)
                .password(this.password)
                .build();
    }



}
