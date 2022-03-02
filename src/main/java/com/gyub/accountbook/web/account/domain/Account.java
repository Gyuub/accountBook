package com.gyub.accountbook.web.account.domain;

import com.gyub.accountbook.global.domain.BaseEntity;
import com.gyub.accountbook.web.authority.domain.Authority;
import com.gyub.accountbook.web.sharing.domain.Sharing;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor
@Where(clause = "delete_yn = 'N'")
public class Account extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @Column(name = "account_name", nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "account")
    private List<AccountDetail> accountDetails = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    private List<Authority> accountAuthorities = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    private List<Sharing> accountSharings = new ArrayList<>();


    @Builder
    public Account(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    //==편의메소드==//
    public void addAuthority(Authority authority){
        this.accountAuthorities.add(authority);
        authority.addAccount(this);
    }

    //==비즈니스 로직==//
    public void delete(){
        this.deleteFlag = 'Y';
    }

    public void update(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account{" +
                "createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", deleteFlag=" + deleteFlag +
                ", name='" + name + '\'' +
                '}';
    }
}
