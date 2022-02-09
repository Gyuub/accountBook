package com.gyub.accountbook.account.service;

import com.gyub.accountbook.global.dto.account.AccountDetailDto;
import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.account.domain.detail.AccountDetail;

import com.gyub.accountbook.web.account.domain.detail.Category;
import com.gyub.accountbook.web.account.service.AccountDetailService;
import com.gyub.accountbook.web.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;


@SpringBootTest
@Slf4j
public class AccountDetailServiceTest {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountDetailService accountDetailService;

    @BeforeEach
    @DisplayName("가계부 내역 저장하기전 작업")
    public void 가계부_생성(){
        Account account = Account.builder()
                .name("테스트 가계뿌")
                .build();
        //accountService.save(account); //가계부 저장
    }

    @Test
    public void 가계부_내역_저장() {
        //Given
        Account account = accountService.findOne(3L);


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
        log.debug("==============================================");
        log.debug(accountDetailService.findOne(saveId).toString());
        log.debug("==============================================");

    }

    @Test
    public void 가계부_내역_수정(){

        //Given
        AccountDetailDto detailDto = new AccountDetailDto();
        detailDto.setId(4L);
        detailDto.setAmount(30000);
        detailDto.setTitle("제목 변경 테스트");
        detailDto.setContents("내용 변경 테스트 10000 - > 30000으로 변경");

        AccountDetail accountDetail = detailDto.toEntity();

        //When
        accountDetailService.update(accountDetail);

        //Then
        assertEquals(detailDto.getAmount(),
                accountDetailService.findOne(detailDto.getId()).getAmount());
    }

    @Test
    @Rollback(value = false)
    public void 가계부_내역_삭제(){
        //Given
        Long accountDetailId = 5L;

        //When
        accountDetailService.delete(accountDetailId);

        //Then
        assertEquals('Y', accountDetailService.findOne(accountDetailId).getDeleteFlag());
    }


}