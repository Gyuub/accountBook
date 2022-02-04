package com.gyub.accountbook.account.domain.detail;

import com.gyub.accountbook.account.domain.Account;
import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity @Getter
@Builder
public class AccountDetail {

    @Id
    @GeneratedValue
    @Column(name = "account_deatil_id")
    private Long id;


    private String title;
    private String contents;
    private Integer amount;
    private String writer;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne
    @JoinColumn(name = "category_id")
    @Nullable
    private Category category;


    //==생성자 메소드==//

}
