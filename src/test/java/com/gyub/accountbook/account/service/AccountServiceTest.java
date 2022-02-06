package com.gyub.accountbook.account.service;

import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.account.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
@Transactional
public class AccountServiceTest {
    @Autowired
    AccountService accountService;

    @Test
    @Rollback(value = false)
    public void 가계부_생성(){
        //Given
        Account account = new Account("테스트 가계부");

        //When
        Long saveId = accountService.save(1L, account);

        //Then
        assertSame(account, accountService.findOne(saveId));
    }



}