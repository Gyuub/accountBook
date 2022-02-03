package com.gyub.accountbook.account.domain;

import com.gyub.accountbook.account.domain.detail.AccountDetail;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @OneToMany(mappedBy = "account")
    private List<AccountDetail> accountDetails = new ArrayList<>();

}
