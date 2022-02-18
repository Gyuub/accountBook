package com.gyub.accountbook.global.dto.sharing;

import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.sharing.domain.Sharing;
import com.gyub.accountbook.web.sharing.domain.SharingState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SharingRequestDto {
    private Long id;
    private Long fromMemberId;
    private Long toMemberId;
    private Long accountId;
    private SharingState sharingState;

    public Sharing toEntity(){
        return Sharing.builder()
                .id(this.id)
                .fromMember(Member.builder().id(this.fromMemberId).build())
                .toMember(Member.builder().id(this.toMemberId).build())
                .account(Account.builder().id(accountId).build())
                .sharingState(this.sharingState)
                .build();
    }
}
