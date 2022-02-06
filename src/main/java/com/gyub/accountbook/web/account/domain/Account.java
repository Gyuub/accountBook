package com.gyub.accountbook.web.account.domain;

import com.gyub.accountbook.global.domain.BaseEntity;
import com.gyub.accountbook.web.account.domain.detail.AccountDetail;
import com.gyub.accountbook.web.member.domain.Member;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
public class Account extends BaseEntity {

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


    //==비즈니스 로직==//
    public void delete(){
        this.deleted = true;
    }
}
