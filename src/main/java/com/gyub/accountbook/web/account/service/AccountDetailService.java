package com.gyub.accountbook.web.account.service;

import com.gyub.accountbook.global.dto.account.AccountDetailDto;
import com.gyub.accountbook.global.exception.ErrorCode;
import com.gyub.accountbook.global.exception.custom.AccountUnauthorizedException;
import com.gyub.accountbook.global.exception.custom.InvalidValueException;
import com.gyub.accountbook.global.exception.custom.MemberNotFoundException;
import com.gyub.accountbook.global.util.SecurityUtil;
import com.gyub.accountbook.web.account.domain.AccountDetail;
import com.gyub.accountbook.web.account.repository.AccountDetailRepository;
import com.gyub.accountbook.web.account.repository.AccountQueryRepository;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountDetailService {
    private final AccountDetailRepository accountDetailRepository;
    private final AccountQueryRepository accountQueryRepository;

    private final MemberRepository memberRepository;

    //내역 조회
    @Transactional
    public AccountDetailDto findOne(Long accountDetailId) {
        return accountDetailRepository.findById(accountDetailId)
                .map(accountDetail -> AccountDetailDto.from(accountDetail))
                .orElseThrow(() -> new InvalidValueException("가계부 내역 키 값이 잘못 되었습니다."
                        , ErrorCode.INVALID_VALUE));
    }

    @Transactional(readOnly = true)
    public List<AccountDetailDto> findAccountDetails(Long accountId) {
        return accountQueryRepository.findByAccounts(accountId)
                .stream()
                .map(accountDetail -> AccountDetailDto.from(accountDetail))
                .collect(Collectors.toList());
    }

    //내역 생성
    @Transactional
    public AccountDetailDto save(AccountDetail accountDetail) {
        String email = SecurityUtil.getCurrentUserEmail();

        Member writerMember = memberRepository.findOneByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("", ErrorCode.MEMBER_NOT_FOUND));
        accountDetail.addMember(writerMember);

        return AccountDetailDto.from(
                accountDetailRepository.save(accountDetail)
        );
    }

    //내역 수정
    @Transactional
    public AccountDetailDto update(AccountDetail accountDetail) {
        //내역 조회
        AccountDetail findDetail = accountDetailRepository
                .findById(accountDetail.getId())
                .orElseThrow(() -> new InvalidValueException("가계부 내역 키 값이 잘못 되었습니다."
                        , ErrorCode.INVALID_VALUE));

        findDetail.update(
                accountDetail.getTitle()
                , accountDetail.getContents()
                , accountDetail.getAmount()
                , accountDetail.getCategory()
        );
        return AccountDetailDto.from(findDetail);
    }

    //내역 삭제
    @Transactional
    public void delete(Long accountDetailId) {
        AccountDetail findDetail = accountDetailRepository
                .findById(accountDetailId)
                .orElseThrow(() -> new InvalidValueException("가계부 내역 키 값이 잘못 되었습니다."
                        , ErrorCode.INVALID_VALUE));
        findDetail.delete();
    }




}
