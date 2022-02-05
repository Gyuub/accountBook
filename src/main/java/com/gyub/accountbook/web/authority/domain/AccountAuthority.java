package com.gyub.accountbook.web.authority.domain;

import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.member.domain.Member;

import javax.persistence.*;

@Entity
public class AccountAuthority {
    @Id @GeneratedValue
    @Column(name = "authority_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Enumerated(EnumType.STRING)
    private Role role;

}
