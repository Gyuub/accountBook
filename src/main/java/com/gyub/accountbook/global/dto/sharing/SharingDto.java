package com.gyub.accountbook.global.dto.sharing;

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
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SharingDto {
    private Long id;
    private MemberDto fromMember;
    private MemberDto toMember;
    private AccountDto account;
    private SharingState sharingState;

    public static SharingDto from(Sharing sharing){
        return SharingDto.builder()
                .id(sharing.getId())
                .fromMember(MemberDto.from(sharing.getFromMember()))
                .toMember(MemberDto.from(sharing.getToMember()))
                .account(AccountDto.from(sharing.getAccount()))
                .sharingState(sharing.getSharingState())
                .build();
    }
}
