package com.gyub.accountbook.global.dto.account;

import com.gyub.accountbook.web.account.domain.AccountDetail;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailDto {

    private Long id;

    private String title;
    private String contents;
    private Integer amount;

    private CategoryDto category;

    public static AccountDetailDto from(AccountDetail accountDetail){
        return AccountDetailDto.builder()
                .id(accountDetail.getId())
                .title(accountDetail.getTitle())
                .contents(accountDetail.getContents())
                .amount(accountDetail.getAmount())
                .category(CategoryDto.from(accountDetail.getCategory()))
                .build();
    }

}
