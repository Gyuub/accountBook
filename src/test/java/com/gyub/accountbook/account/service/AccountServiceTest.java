package com.gyub.accountbook.account.service;

import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.account.service.AccountService;
import com.gyub.accountbook.web.authority.service.AuthorityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertSame;

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
        Account account = new Account("테스트 가계부2");

        //When
        Long saveId = accountService.save(memberId, account);

        //Then
        assertSame(account, accountService.findOne(saveId));
    }



}