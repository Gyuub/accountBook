package com.gyub.accountbook.web.account.domain.detail;

import com.gyub.accountbook.global.dto.account.AccountDetailDto;
import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.global.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class AccountDetail extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "account_detail_id")
    private Long id;

    private String title;
    private String contents;
    private Integer amount;
    private String writer;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = true)
    private Category category;

    //==생성자 패턴==//
    @Builder
    public AccountDetail(Long id,String title, String contents, Integer amount,
                         String writer, Account account, Category category) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.amount = amount;
        this.writer = writer;
        this.account = account;
        this.category = category;
    }

    //==비즈니스 로직==//
    public void update(String title, String contents, Integer amount){
        this.title = title;
        this.contents = contents;
        this.amount = amount;
    }

    public void delete(){
        this.deleted = true;
    }
}
