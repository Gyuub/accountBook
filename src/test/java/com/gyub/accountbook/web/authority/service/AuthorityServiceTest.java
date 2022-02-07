package com.gyub.accountbook.web.authority.service;

import com.gyub.accountbook.web.authority.domain.Authority;
import com.gyub.accountbook.web.authority.repository.AuthorityRepository;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AuthorityServiceTest {

    @Autowired
    AuthorityService authorityService;

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("유저가 갖고 있는 권한내 가계부 조회")
    public void 유저_권한내_가계부_조회(){
        //Given
        Long memberId = 2L;

        //When
        Member member = memberService.findOne(memberId);

        //Then
        List<Authority> findAuthoritys = authorityService.findByMemeber(member.getId());
        for (Authority each: findAuthoritys) {
            System.out.println(each.toString());
        }
    }




}