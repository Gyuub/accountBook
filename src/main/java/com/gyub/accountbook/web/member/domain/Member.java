package com.gyub.accountbook.web.member.domain;


import com.gyub.accountbook.web.authority.domain.Authority;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor
public class Member{
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String email;
    private String nickname;
    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Authority> authoritys = new ArrayList<>();

    //==생성자메소드==//
    @Builder
    public Member(Long id, String email, String nickname, String password) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    //==비즈니스 로직==//
    public void update(String nickname){
        this.nickname = nickname;
    }
    public void changePassword(String password){
        this.password = password;
    }


    @Override
    public String toString() {
        return "Member{" +
                "email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
