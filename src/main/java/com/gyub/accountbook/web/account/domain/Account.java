package com.gyub.accountbook.web.account.domain;

import com.gyub.accountbook.global.domain.BaseEntity;
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

    @Column(name = "account_name")
    @NotNull
    private String name;

    @OneToMany(mappedBy = "account")
    private List<AccountDetail> accountDetails = new ArrayList<>();


    @Builder
    public Account(Long id, String name) {
        this.id = id;
        this.name = name;
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
