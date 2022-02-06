package com.gyub.accountbook.web.member.domain;


import com.gyub.accountbook.web.authority.domain.Authority;
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



    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Authority> authoritys = new ArrayList<>();

    //==생성자메소드==//
    public Member() {}

    public Member(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }


}
