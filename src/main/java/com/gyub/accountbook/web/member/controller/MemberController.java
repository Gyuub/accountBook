package com.gyub.accountbook.web.member.controller;


import com.gyub.accountbook.global.dto.member.AddMemberDto;
import com.gyub.accountbook.global.dto.member.MemberDto;
import com.gyub.accountbook.global.dto.member.ModifyMemberDto;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    private final MemberService memberService;


    @PostMapping("/signup")
    public ResponseEntity<String> addMember(@RequestBody AddMemberDto memberDto){
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .nickname(memberDto.getNickname())
                .password(memberDto.getPassword())
                .build();
        memberService.join(member);
        return ResponseEntity.ok("정상적으로 회원 가입이 되었습니다.");
    }

    @GetMapping("/member")
    public ResponseEntity<MemberDto> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(memberService.getMemberInfo());
    }

    @PutMapping(value = "/member")
    public void modifyMember(@RequestBody ModifyMemberDto memberDto){
        memberService.modify(memberDto.getId(), memberDto.getNickname(), memberDto.getPassword());
    }

    @DeleteMapping(value = "/member")
    public void deleteMember(@PathVariable("id")Long memberId){

    }
}
