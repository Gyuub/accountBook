package com.gyub.accountbook.account.domain;

import com.gyub.accountbook.account.domain.detail.AccountDetail;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @Column(name = "account_name")
    @NotNull
    private String name;

    @OneToMany(mappedBy = "account")
    private List<AccountDetail> accountDetails = new ArrayList<>();

    public Account() {
    }

    public Account(String name) {
        this.name = name;
    }
}
