package com.gyub.accountbook.global.dto.sharing;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gyub.accountbook.global.dto.account.AccountDto;
import com.gyub.accountbook.global.dto.member.MemberDto;
import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.sharing.domain.Sharing;
import com.gyub.accountbook.web.sharing.domain.SharingState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Description;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SharingAccountDto {

    private Long sharingId;
    private Long accountId;
    private String email;
    private String nickname;
    private SharingState sharingState;
    private String accountName;

    public static SharingAccountDto from(Sharing sharing){
        return SharingAccountDto.builder()
                .sharingId(sharing.getId())
                .accountId(sharing.getAccount().getId())
                .accountName(sharing.getAccount().getName())
                .email(sharing.getToMember().getEmail())
                .nickname(sharing.getToMember().getNickname())
                .sharingState(sharing.getSharingState())
                .build();
    }
}
