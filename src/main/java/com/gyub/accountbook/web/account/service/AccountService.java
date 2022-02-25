package com.gyub.accountbook.web.account.service;

import com.gyub.accountbook.global.dto.account.AccountDto;
import com.gyub.accountbook.global.exception.ErrorCode;
import com.gyub.accountbook.global.exception.custom.AccountUnauthorizedException;
import com.gyub.accountbook.global.exception.custom.InvalidValueException;
import com.gyub.accountbook.global.exception.custom.MemberNotFoundException;
import com.gyub.accountbook.global.util.SecurityUtil;
import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.account.repository.AccountRepository;
import com.gyub.accountbook.web.authority.domain.Authority;
import com.gyub.accountbook.web.authority.domain.Role;
import com.gyub.accountbook.web.authority.repository.AuthorityRepository;
import com.gyub.accountbook.web.authority.service.AuthorityService;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.member.repository.MemberRepository;
import com.gyub.accountbook.web.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {
    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;
    private final AuthorityService authorityService;

    //가계부 조회
    public Account findOne(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new InvalidValueException("accountId : " + accountId, ErrorCode.INVALID_VALUE));
    }

    public List<Account> findAccounts() {
        return accountRepository.findAll();
    }


    @Transactional
    public AccountDto save(Account account) {
        //사용자 조회
        String email = SecurityUtil.getCurrentUserEmail();

        Member writerMember = memberRepository.findOneByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("", ErrorCode.MEMBER_NOT_FOUND));

        //가계부 생성
        accountRepository.save(account);

        //권한
        authorityService.save(writerMember, account, Role.OWNER);

        return AccountDto.from(account);
    }

    //가계부 수정
    @Transactional
    public AccountDto update(Account account) {
        //사용자 조회
        String email = SecurityUtil.getCurrentUserEmail();

        Member writerMember = memberRepository.findOneByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("", ErrorCode.MEMBER_NOT_FOUND));

        validateAccountUnauthorized(writerMember.getId(), account.getId());

        //영속화
        Account findAccount = findOne(account.getId());
        findAccount.update(account.getName());
        return AccountDto.from(findAccount);
    }


    //가계부 삭제
    @Transactional
    public void delete(Long accountId) {
        //사용자 조회
        String email = SecurityUtil.getCurrentUserEmail();

        Member writerMember = memberRepository.findOneByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("", ErrorCode.MEMBER_NOT_FOUND));

        validateAccountUnauthorized(writerMember.getId(), accountId);

        //영속화
        Account account = findOne(accountId);
        account.delete();
    }



    public void validateAccountUnauthorized(Long memberId, Long accountId){
        if (!authorityService.isAuthorityOwner(memberId, accountId)) {
            throw new AccountUnauthorizedException(
                    "memberId, accountId :" + memberId + "," + accountId
                    , ErrorCode.ACCOUNT_UNAUTHORIZED);
        }
    }
}

