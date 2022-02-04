package com.gyub.accountbook.account.service;

import com.gyub.accountbook.account.domain.Account;
import com.gyub.accountbook.account.domain.detail.AccountDetail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
public class AccountDetailServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountDetailService accountDetailService;

    @BeforeEach
    @DisplayName("가계부 내역 저장하기전 작업")
    public void 가계부_생성(){
        Account account = new Account("가계부1");
        accountService.save(account); //가계부 저장
    }

    @Test
    public void 가계부_내역_저장() {
        //Given
        Account account = accountService.findOne(72L);


        AccountDetail accountDetail = AccountDetail.builder()
                .account(account)
                .title("가계부 내역 제목")
                .contents("중국집가서 당면을 먹었다. like 중국당면")
                .writer("테스터")
                .amount(20000)
                .build();

        //When

        Long saveId = accountDetailService.save(accountDetail);//내역 저장

        //Then
        Assertions.assertEquals(accountDetail, accountDetailService.findOne(saveId));

    }


}