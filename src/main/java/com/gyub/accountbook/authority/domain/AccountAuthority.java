package com.gyub.accountbook.authority.domain;

import com.gyub.accountbook.account.domain.Account;
import com.gyub.accountbook.member.domain.Member;

import javax.persistence.*;

@Entity
public class AccountAuthority {
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
    private Role role;

}
