package com.gyub.accountbook.account.service;

import com.gyub.accountbook.global.dto.account.AccountDto;
import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.account.service.AccountService;
import com.gyub.accountbook.web.authority.service.AuthorityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AccountServiceTest {
    @Autowired
    AccountService accountService;

    @Autowired
    AuthorityService authorityService;

    @Test
    @Rollback(value = false)
    public void 가계부_생성(){
        //Given
        Long memberId = 2L;
        Account account = Account.builder()
                                .name("테스트 가계부2")
                                .build();

        //When
        Long saveId = accountService.save(memberId, account);

        //Then
        assertSame(account, accountService.findOne(saveId));
    }

    @Test
    @Rollback(value = false)
    public void 가계부_수정(){
        //Given
        Long memberId = 2L;
        Long accountId = 3L;
        String name = "가계부 수정 테스트";

        //When
        accountService.update(memberId, accountId, name);

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
        accountService.delete(memberId, accountId);

        //Then
        assertEquals('Y', accountService.findOne(accountId).getDeleteFlag());
    }

    @Test
    public void 특정_가계부_조회(){
    }

    @Test
    public void 가계부_목록_조회(){
    }

    public void 가계부_목록_조건_조회(){

    }



}