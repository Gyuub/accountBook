package com.gyub.accountbook.web.account.controller;


import com.gyub.accountbook.global.dto.ResultListResponse;
import com.gyub.accountbook.global.dto.ResultResponse;
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
    public ResponseEntity<ResultResponse> saveAccount(
            @RequestBody AccountRequestDto accountRequestDto
    ) {
        return ResponseEntity.ok()
                .body(new ResultResponse(accountService.save(accountRequestDto.toEntity())
                        ,"새로운 가계부가 탄생했습니다. "));
    }

    @PutMapping("/account")
    public ResponseEntity<ResultResponse> modifyAccount(
            @RequestBody AccountRequestDto accountRequestDto
    ) {
        return ResponseEntity.ok()
                .body(new ResultResponse(accountService.update(accountRequestDto.toEntity())
                        ,"가계부가 수정 되었습니다. "));

    }

    @DeleteMapping("/account")
    public ResponseEntity<ResultResponse> deleteAccountDetail(
    ) {
        //accountDetailService.delete(detailId);
        return ResponseEntity.ok()
                .body(new ResultResponse("","ㅠㅠ.. 가계부가 삭제 되었습니다. "));
    }

    @Data
    @AllArgsConstructor
    static class AccountResponseDto {
        private String message;
    }
}
