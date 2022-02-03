package com.gyub.accountbook.account.service;

import com.gyub.accountbook.account.domain.Account;
import com.gyub.accountbook.account.repository.AccountRepository;
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
public class AccountServiceTest {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void 가계부_생성(){
        //Given
        Account account = new Account("테스트 가계부");

        //When
        Long saveId = accountRepository.save(account);

        //Then
        assertEquals(account, accountRepository.findOne(saveId));
    }
}