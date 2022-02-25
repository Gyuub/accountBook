package com.gyub.accountbook.web.service;

import com.gyub.accountbook.global.dto.account.AccountDto;
import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.account.service.AccountService;
import com.gyub.accountbook.web.authority.domain.Authority;
import com.gyub.accountbook.web.authority.service.AuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
public class AccountServiceTest {
    @Autowired
    AccountService accountService;

    @Autowired
    AuthorityService authorityService;

    @Test
    @Rollback(value = false)
    public void 가계부_생성(){
        //Given
        Long memberId = 1L;
        Account account = Account.builder()
                                .name("나만의 가계뿌")
                                .build();

        //When
        AccountDto save = accountService.save(account);

        //Then
        assertSame(account, accountService.findOne(save.getId()));
    }

    @Test
    @Rollback(value = false)
    public void 가계부_수정(){
        //Given
        Long accountId = 3L;
        String name = "가계부 수정 테스트";
        Account account = Account.builder()
                .id(accountId)
                .name("나만의 가계뿌")
                .build();


        //When
        accountService.update(account);

        //Then
        assertEquals(name,accountService.findOne(accountId).getName());
    }

    @Test
    @Rollback(value = false)
    public void 가계부_삭제(){
        //Given
        Long memberId = 2L;
        Long accountId = 3L;

        //When
        accountService.delete(accountId);

        //Then
        assertEquals('Y', accountService.findOne(accountId).getDeleteFlag());
    }

    @Test
    public void 특정_가계부_조회(){
    }

    @Test
    public void 가계부_목록_조회(){
        //Given
        Long memberId = 1L;

        //When
        List<Authority> authorities = authorityService.findByMember(memberId);

        //Then
        log.debug("======================");
        authorities.stream().forEach(authority -> log.debug(authority.getAccount().getName()));
        log.debug("======================");
    }

    @Test
    public void 가계부_목록_조건_조회(){


        LocalDateTime to;

    }




}