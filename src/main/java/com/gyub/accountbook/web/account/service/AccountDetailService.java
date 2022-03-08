package com.gyub.accountbook.web.account.service;

import com.gyub.accountbook.global.dto.account.AccountDetailDto;
import com.gyub.accountbook.global.dto.account.stats.AccountDetailStatsDto;
import com.gyub.accountbook.global.dto.account.stats.AccountDetailYearStatsDto;
import com.gyub.accountbook.global.exception.ErrorCode;
import com.gyub.accountbook.global.exception.custom.InvalidValueException;
import com.gyub.accountbook.global.exception.custom.MemberNotFoundException;
import com.gyub.accountbook.global.util.SecurityUtil;
import com.gyub.accountbook.web.account.domain.AccountDetail;
import com.gyub.accountbook.web.account.repository.AccountDetailQueryRepository;
import com.gyub.accountbook.web.account.repository.AccountDetailRepository;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountDetailService {
    private final AccountDetailRepository accountDetailRepository;

    private final AccountDetailQueryRepository accountDetailQueryRepository;

    private final MemberRepository memberRepository;

    //내역 조회
    public AccountDetailDto findOne(Long accountDetailId) {
        return accountDetailRepository.findById(accountDetailId)
                .map(accountDetail -> AccountDetailDto.from(accountDetail))
                .orElseThrow(() -> new InvalidValueException("가계부 내역 키 값이 잘못 되었습니다."
                        , ErrorCode.INVALID_VALUE));
    }


    public List<AccountDetailDto> findAllByDateMonth(Long accountId, LocalDate dateMonth) {

        validateParameter(dateMonth);

        LocalDateTime from = dateMonth.atTime(0, 0).with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime to = dateMonth.atTime(0, 0).with(TemporalAdjusters.lastDayOfMonth());
        return accountDetailQueryRepository.findAllByDateMonth(accountId, from, to)
                .stream()
                .map(accountDetail -> AccountDetailDto.from(accountDetail))
                .collect(Collectors.toList());
    }
    public List<AccountDetailStatsDto> findStatsByAccount(Long accountId, LocalDate dateMonth) {

        validateParameter(dateMonth);

        LocalDateTime from = dateMonth.atTime(0, 0).with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime to = dateMonth.atTime(0, 0).with(TemporalAdjusters.lastDayOfMonth());

        from.truncatedTo(ChronoUnit.SECONDS);
        to.truncatedTo(ChronoUnit.SECONDS);

        return accountDetailQueryRepository.findStatsByAccount(accountId, from, to);
    }

    /**
     * 년도별 통계
     * @param accountId
     * @param dateMonth 해당 일자로부터 -11개월
     * @return
     */
    public List<AccountDetailYearStatsDto> findYearStatsByAccount(Long accountId, LocalDate dateMonth) {

        validateParameter(dateMonth);

        LocalDateTime from = dateMonth.atTime(0, 0).minusYears(1).with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime to = dateMonth.atTime(0, 0).minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

        from.truncatedTo(ChronoUnit.SECONDS);
        to.truncatedTo(ChronoUnit.SECONDS);
        return accountDetailQueryRepository.findYearStatsByAccount(accountId, from, to);
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
                , accountDetail.getWriteDate()
                , accountDetail.getDetailCd()
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


    private LocalDateTime convertLocalDate(String dateMonth){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDateTime.parse(dateMonth, formatter);
    }

    private void validateParameter(LocalDate param){
        if(param == null){
            throw new InvalidValueException("param :" + param, ErrorCode.INVALID_VALUE);
        }
    }

}
