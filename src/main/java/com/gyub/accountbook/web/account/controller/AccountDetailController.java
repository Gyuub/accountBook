package com.gyub.accountbook.web.account.controller;


import com.gyub.accountbook.global.dto.ResultListResponse;
import com.gyub.accountbook.global.dto.ResultResponse;
import com.gyub.accountbook.global.dto.account.AccountDetailDto;
import com.gyub.accountbook.global.dto.account.AccountDetailRequestDto;
import com.gyub.accountbook.global.dto.account.AccountDetailStatsDto;
import com.gyub.accountbook.web.account.domain.AccountDetail;
import com.gyub.accountbook.web.account.service.AccountDetailService;
import com.gyub.accountbook.web.account.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<ResultListResponse> findAllAccountDetails(
            @PathVariable(value = "accountid") Long accountId,
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        List<AccountDetailDto> accountDetails = accountDetailService.findAllByDateMonth(accountId, date);
        return ResponseEntity.ok()
                .body(new ResultListResponse(accountDetails, accountDetails.size(), ""));
    }
    @GetMapping("/account/stats/{accountid}")
    public ResponseEntity<ResultListResponse> findAllAccountDetailStats(
            @PathVariable(value = "accountid") Long accountId,
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        List<AccountDetailStatsDto> statsByAccount = accountDetailService.findStatsByAccount(accountId, date);

        return ResponseEntity.ok()
                .body(new ResultListResponse(statsByAccount, statsByAccount.size(), ""));
    }

    @PostMapping("/account/{accountid}")
    public ResponseEntity<ResultResponse> saveAccountDetail(
            @PathVariable(value = "accountid") Long accountId,
            @RequestBody AccountDetailRequestDto accountDetailRequestDto
    ) {
        accountDetailRequestDto.setAccountId(accountId);
        AccountDetail accountDetail = accountDetailRequestDto.toEntity();

        return ResponseEntity.ok()
                .body(new ResultResponse(accountDetailService.save(accountDetail),"굿굿 ~ 내역이 저장 되었습니다."));
    }

    @PutMapping("/account/{accountid}")
    public ResponseEntity<ResultResponse> modifyAccountDetail(
            @RequestBody AccountDetailRequestDto accountDetailRequestDto
    ) {
        AccountDetail accountDetail = accountDetailRequestDto.toEntity();
        return ResponseEntity.ok()
                .body(new ResultResponse(accountDetailService.update(accountDetail),"내역이 수정 되었습니다."));
    }

    @DeleteMapping("/account/{accountid}")
    public ResponseEntity<ResultResponse> deleteAccountDetail(
            @PathVariable(value = "accountid") Long accountId,
            @RequestParam(value = "detailid") Long detailId
    ) {
        accountDetailService.delete(detailId);
        return ResponseEntity.ok()
                .body(new ResultResponse("","내역이 삭제 되었습니다."));
    }

    @Data
    @AllArgsConstructor
    static class AccountDetailResponseDto {
        private ResultListResponse detail;
        private ResultListResponse stats;
    }
}
