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
    private String fromMemberEmail;
    private String toMemberEmail;
    private Long accountId;
    private SharingState sharingState;


}
