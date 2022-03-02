package com.gyub.accountbook.global.dto.account;


import com.gyub.accountbook.global.dto.sharing.SharingAccountDto;
import com.gyub.accountbook.web.account.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountSharingDto {

    private Long id;
    private String name;
    private List<SharingAccountDto> sharingAccount = new ArrayList<>();

    public static AccountSharingDto from(Account account) {
        return AccountSharingDto.builder()
                .id(account.getId())
                .name(account.getName())
                .sharingAccount(
                        account.getAccountSharings().stream()
                                .map(sharing -> SharingAccountDto.from(sharing))
                                .collect(Collectors.toList())
                )
                .build();
    }
}
