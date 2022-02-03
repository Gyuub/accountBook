package com.gyub.accountbook.member.domain;


import com.gyub.accountbook.authority.domain.AccountAuthority;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
public class Member{
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String email;
    private String nickname;

    public Member() {}

    public Member(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    @OneToMany(mappedBy = "member")
    private List<AccountAuthority> authoritys = new ArrayList<>();

}
