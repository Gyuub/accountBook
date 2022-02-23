package com.gyub.accountbook.web.member.controller;

import com.gyub.accountbook.global.dto.member.LoginMemberAuthorityDto;
import com.gyub.accountbook.global.dto.member.TokenMemberDto;
import com.gyub.accountbook.global.configuration.jwt.JwtFilter;
import com.gyub.accountbook.global.configuration.jwt.TokenProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDto> authenticateMember(@RequestBody LoginMemberAuthorityDto memberDto) {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(memberDto.getEmail(), memberDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return ResponseEntity.ok()
                .header(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt)
                .body(new AuthResponseDto("로그인 되었습니다.",jwt));
    }

    @Data
    @AllArgsConstructor
    static class AuthResponseDto {
        private String message;
        private String token;
    }


}
