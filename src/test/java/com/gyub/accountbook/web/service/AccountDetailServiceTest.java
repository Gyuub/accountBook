package com.gyub.accountbook.web.service;

import com.gyub.accountbook.global.dto.account.AccountDetailDto;
import com.gyub.accountbook.global.dto.account.AccountDetailRequestDto;
import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.account.domain.AccountDetail;
import com.gyub.accountbook.web.account.repository.AccountDetailRepository;
import com.gyub.accountbook.web.account.service.AccountDetailService;
import com.gyub.accountbook.web.account.service.AccountService;
import com.gyub.accountbook.web.member.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Slf4j
public class AccountDetailServiceTest {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountDetailService accountDetailService;

    @Autowired
    AccountDetailRepository accountDetailRepository;



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
        AccountDetail accountDetail = AccountDetail.builder()
                .account(Account.builder().id(1L).build())
                .title("가계부 내역 제목")
                .contents("중국집가서 당면을 먹었다. like 중국당면")
                .member(Member.builder().id(9L).build())
                .amount(20000)
                .build();

        //When
        AccountDetailDto save = accountDetailService.save(accountDetail);//내역 저장

        //Then
        log.debug("==============================================");
        log.debug(save.toString());
        log.debug("==============================================");

    }

    @Test
    public void 가계부_내역_수정(){

        //Given
        AccountDetailRequestDto detailDto = AccountDetailRequestDto.builder()
                .id(4L)
                .amount(30000)
                .title("제목 변경 테스트")
                .contents("내용 변경 테스트 10000 - > 30000으로 변경")
                .build();
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
        assertEquals('Y', accountDetailRepository.findById(accountDetailId).get().getDeleteFlag());
    }


}

