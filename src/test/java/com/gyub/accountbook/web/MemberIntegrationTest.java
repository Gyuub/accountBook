package com.gyub.accountbook.web;

import com.gyub.accountbook.global.dto.member.AddMemberDto;
import com.gyub.accountbook.web.account.service.AccountService;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.member.service.MemberService;
import com.gyub.accountbook.web.sharing.service.SharingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberIntegrationTest {
    @Autowired
    MemberService memberService;
    @Autowired
    AccountService accountService;
    @Autowired SharingService sharingService;

    @Test
    public void 회원가입_및_로그인(){
        //Given
        AddMemberDto memberDto = new AddMemberDto("test@naver.com", "굽인이", "1234");
        Member member = memberDto.toEntity();

        //When
        memberService.save(member);


        //Then
    }

}
