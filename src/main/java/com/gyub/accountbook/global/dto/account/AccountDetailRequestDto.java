package com.gyub.accountbook.global.dto.account;

import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.account.domain.AccountDetail;
import com.gyub.accountbook.web.account.domain.Category;
import com.gyub.accountbook.web.member.domain.Member;
import lombok.*;

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
    private Long memberId;
    private Long accountId;
    private Long categoryId;

    public AccountDetail toEntity(){
        return AccountDetail.builder()
                .id(this.id)
                .title(this.title)
                .contents(this.contents)
                .amount(this.amount)
                .account(Account.builder().id(accountId).build())
                .category(Category.builder().id(categoryId).build())
                .member(Member.builder().id(memberId).build())
                .build();
    }
}
