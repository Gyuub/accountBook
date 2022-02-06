package com.gyub.accountbook.member.service;

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
    @Rollback(value = false)
    public void 회원가입(){
        //Given
        Member member = new Member("test@naver.com", "GYub");

        //When
        Long saveId = memberService.join(member);

        //Then
        assertSame(member, memberService.findOne(saveId));
    }

    @Test
    public void 중복_회원가입(){
        assertThrows(IllegalStateException.class, () -> {
            //Given
            Member member1 = new Member("test@naver.com", "GYub1");
            Member member2 = new Member("test@naver.com", "GYub1");

            //When
            memberService.join(member1);
            memberService.join(member2); //예외가 발생해야 한다

            //Then
            fail("에외가 발생해야 한다.");
        });
    }

}