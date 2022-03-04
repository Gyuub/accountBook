package com.gyub.accountbook.web.authority.controller;

import com.gyub.accountbook.global.dto.ResultListResponse;
import com.gyub.accountbook.global.dto.authority.AuthorityDto;
import com.gyub.accountbook.global.exception.ErrorCode;
import com.gyub.accountbook.global.exception.custom.MemberNotFoundException;
import com.gyub.accountbook.global.util.SecurityUtil;
import com.gyub.accountbook.web.authority.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthorityController {
    private final AuthorityService authorityService;

    //가계부 권한 조회
    @GetMapping("/authority")
    public ResponseEntity<ResultListResponse> getMyAccountAuthority() {
        List<AuthorityDto> myAccounts = authorityService.getMyAccountAuthorities();

        return ResponseEntity.ok()
                .body(new ResultListResponse(myAccounts, myAccounts.size(), ""));
    }
}
