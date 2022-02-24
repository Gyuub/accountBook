package com.gyub.accountbook.global.dto.account;

import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.account.domain.AccountDetail;
import com.gyub.accountbook.web.account.domain.AccountDetailRole;
import com.gyub.accountbook.web.account.domain.Category;
import com.gyub.accountbook.web.member.domain.Member;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailRequestDto {
    private Long id;
    private String title;
    private String contents;
    private Integer amount;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime writeDate;

    private Long memberId;
    private Long accountId;
    private Long categoryId;
    private AccountDetailRole detailCd;


    public AccountDetail toEntity(){
        return AccountDetail.builder()
                .id(this.id)
                .title(this.title)
                .contents(this.contents)
                .amount(this.amount)
                .writeDate(this.writeDate)
                .account(Account.builder().id(accountId).build())
                .category(Category.builder().id(categoryId).build())
                .member(Member.builder().id(memberId).build())
                .detailCd(detailCd)
                .build();
    }
}
