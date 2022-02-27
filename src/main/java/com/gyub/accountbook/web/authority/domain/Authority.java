package com.gyub.accountbook.web.authority.domain;

import com.gyub.accountbook.global.domain.BaseEntity;
import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity @Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    //==편의 메소드==//
    public void addAccount(Account account){
        this.account = account;
        account.addAuthority(this);
    }

}
