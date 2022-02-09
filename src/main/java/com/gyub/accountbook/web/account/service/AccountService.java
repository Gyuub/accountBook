package com.gyub.accountbook.web.account.service;

import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.account.repository.AccountRepository;
import com.gyub.accountbook.web.authority.domain.Authority;
import com.gyub.accountbook.web.authority.domain.Role;
import com.gyub.accountbook.web.authority.repository.AuthorityRepository;
import com.gyub.accountbook.web.authority.service.AuthorityService;
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
    private final AuthorityService authorityService;


    public Long save(Long memberId, Account account ){
        //사용자 조회
        Member member = memberService.findOne(memberId);

        //가계부 생성
        accountRepository.save(account);

        //권한
        authorityService.save(member, account);

        return account.getId();
    }

    //가계부 수정
    public void update(Long memberId, Long accountId, String name){
        if(!authorityService.isAuthorityOwner(memberId, accountId)){
            throw new IllegalStateException("수정 권한이 없는 가계부 입니다.");
        }
        Account account = findOne(accountId);
        account.update(name);
    }


    //가계부 삭제
    public void delete(Long memberId, Long accountId){
        if(!authorityService.isAuthorityOwner(memberId, accountId)){
            throw new IllegalStateException("삭제 권한이 없는 가계부 입니다.");
        }
        Account account = findOne(accountId);
        account.delete();
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

