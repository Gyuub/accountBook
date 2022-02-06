package com.gyub.accountbook.web.account.service;

import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.account.repository.AccountRepository;
import com.gyub.accountbook.web.authority.domain.Authority;
import com.gyub.accountbook.web.authority.domain.Role;
import com.gyub.accountbook.web.authority.repository.AuthorityRepository;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {
    private final MemberService memberService;
    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;

    //가계부 생성
    public Long save(Long memberId, Account account ){
        //사용자 조회
        Member member = memberService.findOne(memberId);

        //가계부 생성
        accountRepository.save(account);

        //권한
        Authority authority = new Authority(member, account, Role.OWNER);
        authorityRepository.save(authority);

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

    //가계부 삭제
    public void delete(Long id){
        Account account = findOne(id);
        account.delete();
    }


}

