package com.gyub.accountbook.web.authority.controller;

import com.gyub.accountbook.global.dto.authority.AuthorityDto;
import com.gyub.accountbook.global.dto.member.MemberDto;
import com.gyub.accountbook.global.util.SecurityUtil;
import com.gyub.accountbook.web.authority.service.AuthorityService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthorityController {
    private final AuthorityService authorityService;

    //가계부 권한 조회
    @GetMapping("/authority")
    public ResponseEntity<List<AuthorityDto>> getMyAccountAuthority(){
        String email = SecurityUtil.getCurrentUserEmail().orElse(null);
        List<AuthorityDto> myAccountAuthorities = authorityService.getMyAccountAuthorities(email);

//        return ResponseEntity.ok()
//                .body(new Result(myAccountAuthorities, myAccountAuthorities.size()));
        return ResponseEntity.ok()
                .body(myAccountAuthorities);
    }
    @AllArgsConstructor
    static class Result<T> {
        private T data;
        private int count;
    }
}
