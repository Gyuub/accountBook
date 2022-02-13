package com.gyub.accountbook.web.member.domain;


import com.gyub.accountbook.web.authority.domain.Authority;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(
            name = "member_authority",
            joinColumns = {@JoinColumn(name = "member_id", referencedColumnName = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")}
    )
    private Set<MemberAuthority> authorities;

    //==생성자메소드==//
    @Builder
    public Member(Long id, String email, String nickname, String password, Set<MemberAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.authorities = authorities;
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
