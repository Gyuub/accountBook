package com.gyub.accountbook.web.account.controller;


import com.gyub.accountbook.global.dto.ResultListResponse;
import com.gyub.accountbook.global.dto.account.AccountDetailDto;
import com.gyub.accountbook.global.dto.account.AccountDetailRequestDto;
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
public class AccountDetailController {

    private final AccountDetailService accountDetailService;

    @GetMapping("/account/{accountid}/{detailid}")
    public ResponseEntity<AccountDetailDto> findAccountDetailById(
            @PathVariable(value = "accountid") Long accountId,
            @PathVariable(value = "detailid") Long detailId
    ) {
        AccountDetailDto accountDetail = accountDetailService.findOne(detailId);
        return ResponseEntity.ok()
                .body(accountDetail);
    }

    @GetMapping("/account/{accountid}")
    public ResponseEntity<ResultListResponse> getAllAccountDetails(
            @PathVariable(value = "accountid") Long accountId
    ) {
        List<AccountDetailDto> accountDetails = accountDetailService.findAccountDetails(accountId);
        return ResponseEntity.ok()
                .body(new ResultListResponse(accountDetails, accountDetails.size()));
    }

    @PostMapping("/account/{accountid}")
    public ResponseEntity<AccountDetailDto> saveAccountDetail(
            @PathVariable(value = "accountid") Long accountId,
            @RequestBody AccountDetailRequestDto accountDetailRequestDto
    ) {
        accountDetailRequestDto.setAccountId(accountId);
        AccountDetail accountDetail = accountDetailRequestDto.toEntity();

        return ResponseEntity.ok()
                .body(accountDetailService.save(accountDetail));
    }

    @PutMapping("/account/{accountid}")
    public ResponseEntity<AccountDetailDto> modifyAccountDetail(
            @RequestBody AccountDetailRequestDto accountDetailRequestDto
    ) {
        AccountDetail accountDetail = accountDetailRequestDto.toEntity();
        return ResponseEntity.ok()
                .body(accountDetailService.update(accountDetail));
    }

    @DeleteMapping("/account/{accountid}/{detailid}")
    public ResponseEntity<AccountResponseDto> deleteAccountDetail(
            @PathVariable(value = "accountid") Long accountId,
            @PathVariable(value = "detailid") Long detailId
    ) {
        accountDetailService.delete(detailId);
        return ResponseEntity.ok()
                .body(new AccountResponseDto("정상적으로 삭제 되었습니다."));
    }

    @Data
    @AllArgsConstructor
    static class AccountResponseDto {
        private String message;
    }
}
