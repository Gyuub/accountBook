package com.gyub.accountbook.account.service;

import com.gyub.accountbook.account.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class AccountServiceTest {
    @Autowired
    AccountService accountService;

    @Test
    public void 가계부_생성(){
        //Given
        Account account = new Account("테스트 가계부");

        //When
        Long saveId = accountService.save(account);

        //Then
        assertEquals(account, accountService.findOne(saveId));
    }

    @Test
    public void 가계부_내역_생성(){
        //Given
        Account account = new Account("테스트 가계부");

        //When
        Long saveId = accountService.save(account);

        //Then
        assertEquals(account, accountService.findOne(saveId));
    }

}