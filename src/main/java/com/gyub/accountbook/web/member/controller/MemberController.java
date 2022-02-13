package com.gyub.accountbook.web.member.controller;


import com.gyub.accountbook.global.dto.member.AddMemberDto;
import com.gyub.accountbook.global.dto.member.LoginMemberDto;
import com.gyub.accountbook.global.dto.member.ModifyMemberDto;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.member.service.CustomUserDetailsService;
import com.gyub.accountbook.web.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

    private final MemberService memberService;


    @PostMapping("/signup")
    public ResponseEntity<String> addMember(@RequestBody AddMemberDto memberDto){
        logger.debug("들어옴");

        Member member = Member.builder()
                .email(memberDto.getEmail())
                .nickname(memberDto.getNickname())
                .password(memberDto.getPassword())
                .build();
        memberService.join(member);
        return ResponseEntity.ok("TEST");
    }

    @GetMapping(value = "/member:id")
    public void getMember(@PathVariable("id")Long memberId){
        Member member = memberService.findOne(memberId);
    }
    @PutMapping(value = "/member")
    public void editMember(@RequestBody ModifyMemberDto memberDto){
        memberService.modify(memberDto.getId(), memberDto.getNickname(), memberDto.getPassword());
    }

    @DeleteMapping(value = "/member")
    public void deleteMember(@PathVariable("id")Long memberId){

    }
}
