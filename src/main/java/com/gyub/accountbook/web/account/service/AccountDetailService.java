package com.gyub.accountbook.web.account.service;

import com.gyub.accountbook.global.dto.account.AccountDetailDto;
import com.gyub.accountbook.web.account.domain.detail.AccountDetail;
import com.gyub.accountbook.web.account.repository.AccountDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountDetailService {
    private final AccountDetailRepository accountDetailRepository;

    //내역 생성
    @Transactional
    public Long save(AccountDetail accountDetail){
        accountDetailRepository.save(accountDetail);
        return accountDetail.getId();
    }
    //내역 수정
    @Transactional
    public void update(AccountDetail accountDetail){
        AccountDetail findDetail = findOne(accountDetail.getId());
        findDetail.update(
            accountDetail.getTitle()
            , accountDetail.getContents()
            , accountDetail.getAmount()
        );
    }
    //내역 삭제
    @Transactional
    public void delete(Long accountDetailId){
        AccountDetail accountDetail = findOne(accountDetailId);
        accountDetail.delete();
    }

    //내역 조회
    @Transactional(readOnly = true)
    public AccountDetail findOne(Long accountDetailId){
        return accountDetailRepository.findById(accountDetailId)
                .orElseGet(() -> AccountDetail.builder().build());
    }

    @Transactional(readOnly = true)
    public List<AccountDetail> findAccountDetails(Long accountDetailId){
        return accountDetailRepository.findAll();
    }




}
