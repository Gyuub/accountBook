package com.gyub.accountbook.member.service;

import com.gyub.accountbook.global.dto.member.ModifyMemberDto;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;


    @Test
    public void 테스트명() {
        Member member = Member.builder()
                .email("test@naver.com")
                .nickname("GYub2")
                .password("1234")
                .build();


    }

    @Test
    @Rollback(value = false)
    public void 회원가입() {
        //Given
        Member member = Member.builder()
                .email("test@naver.com")
                .nickname("GYub2")
                .password("1234")
                .build();

        //When
        memberService.join(member);

        //Then
        assertSame(member, memberService.findOne(member.getId()));
    }

    @Test
    public void 중복_회원가입() {
        assertThrows(IllegalStateException.class, () -> {
            //Given
            Member member1 = Member.builder()
                    .email("test@naver.com")
                    .nickname("GYub2")
                    .password("1234")
                    .build();
            Member member2 = Member.builder()
                    .email("test@naver.com")
                    .nickname("GYub2")
                    .password("1234")
                    .build();

            //When
            memberService.join(member1);
            memberService.join(member2); //예외가 발생해야 한다

            //Then
            fail("에외가 발생해야 한다.");
        });
    }

    @Test
    @Rollback(value = false)
    public void 유저정보_변경() {
        //Given
        ModifyMemberDto dto = new ModifyMemberDto();
        dto.setId(3L);
        dto.setNickname("굽인이");
        dto.setPassword("1234");

        //When
        memberService.modify(dto.getId(), dto.getNickname(), dto.getPassword());

        Member findMember = memberService.findOne(dto.getId());

        //Then
        assertEquals(findMember.getNickname(),dto.getNickname(), "회원 정보가 변경 되었습니다.");
    }

    @Test
    public void 유정_비밀번호_변경() {

    }

}