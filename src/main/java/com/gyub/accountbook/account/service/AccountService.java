package com.gyub.accountbook.account.service;

import com.gyub.accountbook.account.domain.Account;
import com.gyub.accountbook.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    //가계부 생성
    public Long save(Account account){
        accountRepository.save(account);
        return account.getId();
    }
    //가계부 조회
    public Account findOne(Long accountId){
        return accountRepository.findById(accountId)
                .orElseGet(() -> new Account());
    }
    public List<Account> findAccounts(){
        return accountRepository.findAll();
    }
}
    //가계부 삭제
    //가계부
