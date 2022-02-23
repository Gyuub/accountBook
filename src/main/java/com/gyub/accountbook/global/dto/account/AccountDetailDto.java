package com.gyub.accountbook.global.dto.account;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gyub.accountbook.global.domain.BaseEntity;
import com.gyub.accountbook.global.dto.member.MemberDto;
import com.gyub.accountbook.web.account.domain.AccountDetail;
import com.gyub.accountbook.web.account.domain.AccountDetailRole;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDetailDto extends BaseEntity {

    private Long id;

    private String title;
    private String contents;
    private Integer amount;
    private String writer;
    private AccountDetailRole detailCd;
    private CategoryDto category;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime writeDate;

    public static AccountDetailDto from(AccountDetail accountDetail){
        return AccountDetailDto.builder()
                .id(accountDetail.getId())
                .title(accountDetail.getTitle())
                .contents(accountDetail.getContents())
                .amount(accountDetail.getAmount())
                .category(CategoryDto.from(accountDetail.getCategory()))
                .writer(accountDetail.getMember().getNickname())
                .writeDate(accountDetail.getWriteDate())
                .detailCd(accountDetail.getDetailCd())
                .build();
    }

}
