package com.gyub.accountbook.web.authority.domain;

import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.member.domain.Member;
import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
public class Authority {
    @Id @GeneratedValue
    @Column(name = "authority_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Role role;


    //==생성자 메소드==//
    public Authority(Member member, Account account, Role role) {
        this.member = member;
        this.account = account;
        this.role = role;
    }

    public Authority() {
    }

    //==비즈니스 로직==//




    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", member=" + member +
                ", account=" + account +
                ", role=" + role +
                '}';
    }
}
