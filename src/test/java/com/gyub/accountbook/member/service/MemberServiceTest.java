package com.gyub.accountbook.member.service;

import com.gyub.accountbook.member.domain.Member;
import com.gyub.accountbook.member.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입(){
        //Given
        Member member = new Member("test@naver.com", "GYub");

        //When
        Long saveId = memberService.join(member);

        //Then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원가입(){
        //Given
        Member member1 = new Member("test@naver.com", "GYub");
        Member member2 = new Member("test@naver.com", "GYub");

        //When
        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야 한다

        //Then
        fail("에외가 발생해야 한다.");
    }

}