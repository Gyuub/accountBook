package com.gyub.accountbook.account.domain.detail;

import com.gyub.accountbook.account.domain.Account;

import javax.persistence.*;

@Entity
public class AccountDetail {

    @Id
    @GeneratedValue
    @Column(name = "account_deatil_id")
    private Long id;

    private String title;
    private String contents;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
