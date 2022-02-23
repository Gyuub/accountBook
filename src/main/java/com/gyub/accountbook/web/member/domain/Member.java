package com.gyub.accountbook.web.member.domain;


import com.gyub.accountbook.global.domain.BaseEntity;
import com.gyub.accountbook.web.authority.domain.Authority;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.*;

@Entity @Getter
@NoArgsConstructor
@Where(clause = " delete_yn = 'N' ")
public class Member extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;
    private String nickname;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Authority> authoritys = new ArrayList<>();

    @OneToMany( mappedBy = "member" )
    private Set<MemberAuthority> authorities = new HashSet<>();

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
    public void addAuthority(MemberAuthority memberAuthority){
        memberAuthority.addMember(this);
        this.authorities.add(memberAuthority);
    }


    @Override
    public String toString() {
        return "Member{" +
                "email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
