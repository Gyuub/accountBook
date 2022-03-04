package com.gyub.accountbook.web.account.controller;


import com.gyub.accountbook.global.dto.ResultListResponse;
import com.gyub.accountbook.global.dto.ResultResponse;
import com.gyub.accountbook.global.dto.account.*;
import com.gyub.accountbook.global.dto.sharing.SharingAccountDto;
import com.gyub.accountbook.web.account.service.AccountService;
import com.gyub.accountbook.web.sharing.service.SharingService;
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
    private final SharingService sharingService;

    @GetMapping("/account")
    public ResponseEntity<AccountResponseDto> findAccountAuthorityByEmail(
    ) {
        List<AccountSharingDto> result = accountService.findAccountAuthorityByEmail();
        List<SharingAccountDto> sharings = sharingService.findMemberAccountByEmail();

        return ResponseEntity.ok()
                .body(new AccountResponseDto(
                        new ResultListResponse(result, result.size(), ""),
                        new ResultListResponse(sharings, sharings.size(), "")
                ));
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
        private ResultListResponse account;
        private ResultListResponse sharing;
    }
}
