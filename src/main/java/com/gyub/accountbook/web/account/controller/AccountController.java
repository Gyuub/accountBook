package com.gyub.accountbook.web.account.controller;


import com.gyub.accountbook.global.dto.ResultListResponse;
import com.gyub.accountbook.global.dto.account.AccountDetailDto;
import com.gyub.accountbook.global.dto.account.AccountDetailRequestDto;
import com.gyub.accountbook.global.dto.account.AccountDto;
import com.gyub.accountbook.global.dto.account.AccountRequestDto;
import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.account.domain.AccountDetail;
import com.gyub.accountbook.web.account.service.AccountDetailService;
import com.gyub.accountbook.web.account.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AccountController {

    private final AccountService accountService;
    private final AccountDetailService accountDetailService;

    @GetMapping("/account")
    public void findAccountDetailById(
            @PathVariable(value = "accountId") Long accountId,
            @PathVariable(value = "detailId") Long detailId
    ) {

    }


    @PostMapping("/account")
    public ResponseEntity<AccountDto> saveAccount(
            @RequestBody AccountRequestDto accountRequestDto
    ) {
        return ResponseEntity.ok()
                .body(accountService.save(accountRequestDto.toEntity()));
    }

    @PutMapping("/account")
    public ResponseEntity<AccountDto> modifyAccountDetail(
            @RequestBody AccountRequestDto accountRequestDto
    ) {
        return ResponseEntity.ok()
                .body(accountService.update(accountRequestDto.toEntity()));
    }

    @DeleteMapping("/account")
    public ResponseEntity<AccountResponseDto> deleteAccountDetail(
    ) {
        //accountDetailService.delete(detailId);
        return ResponseEntity.ok()
                .body(new AccountResponseDto("정상적으로 삭제 되었습니다."));
    }

    @Data
    @AllArgsConstructor
    static class AccountResponseDto {
        private String message;
    }
}
