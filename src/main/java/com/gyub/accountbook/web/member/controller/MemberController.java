package com.gyub.accountbook.web.member.controller;


import com.gyub.accountbook.global.dto.member.AddMemberDto;
import com.gyub.accountbook.global.dto.member.ModifyMemberDto;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member")
    public String test(){
        return "/test.html";
    }

    @PostMapping(value = "/member")
    public void addMember(@RequestBody AddMemberDto memberDto){
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .nickname(memberDto.getNickname())
                .password(memberDto.getPassword())
                .build();
        memberService.join(member);
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
