package com.gyub.accountbook.web.member.controller;


import com.gyub.accountbook.global.dto.ResultListResponse;
import com.gyub.accountbook.global.dto.member.AddMemberDto;
import com.gyub.accountbook.global.dto.member.MemberAccountDto;
import com.gyub.accountbook.global.dto.member.MemberDto;
import com.gyub.accountbook.global.dto.member.ModifyMemberDto;
import com.gyub.accountbook.global.util.SecurityUtil;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.member.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    private final MemberService memberService;

    @GetMapping("/member")
    public ResponseEntity<MemberDto> getMyUserInfo(HttpServletRequest request) {
        String email = SecurityUtil.getCurrentUserEmail();
        return ResponseEntity.ok()
                .body(memberService.getMemberInfo(email));
    }

    @GetMapping("/member/account")
    public ResponseEntity<ResultListResponse> findAllByAccountAuthority(HttpServletRequest request) {
        List<MemberAccountDto> result = memberService.findAllByAccountAuthority();
        return ResponseEntity.ok()
                .body(new ResultListResponse(result, result.size(), ""));
    }


    @PostMapping("/signup")
    public ResponseEntity<MemberJoinDto> addMember(@RequestBody AddMemberDto memberDto) {
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .nickname(memberDto.getNickname())
                .password(memberDto.getPassword())
                .build();
        memberService.save(member);
        return ResponseEntity.ok()
                .body(new MemberJoinDto("정상적으로 회원 가입이 되었습니다."));
    }

    @PutMapping(value = "/member")
    public void modifyMember(@RequestBody ModifyMemberDto memberDto) {
        memberService.update(memberDto.getId(), memberDto.getNickname(), memberDto.getPassword());
    }

    @DeleteMapping(value = "/member")
    public void deleteMember(@PathVariable("id") Long memberId) {

    }

    @Data
    @AllArgsConstructor
    static class MemberJoinDto {
        private String message;
    }
}
