package com.gyub.accountbook.web.account.repository;

import com.gyub.accountbook.global.dto.account.AccountDetailStatsDto;
import com.gyub.accountbook.web.account.domain.AccountDetail;
import com.gyub.accountbook.web.account.domain.AccountDetailRole;
import com.gyub.accountbook.web.account.domain.QAccountDetail;
import com.gyub.accountbook.web.account.domain.QCategory;
import com.gyub.accountbook.web.member.domain.QMember;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public class AccountDetailQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public AccountDetailQueryRepository(final JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }


    QAccountDetail accountDetail = new QAccountDetail("accountDetail");
    QCategory category = new QCategory("category");
    QMember member = new QMember("member");

    //조회: 월별 내역
    public List<AccountDetail> findAllByDateMonth(Long accountId, LocalDateTime from, LocalDateTime to) {
        return jpaQueryFactory
                .select(accountDetail)
                .from(accountDetail)
                .innerJoin(accountDetail.member, member).fetchJoin()
                .innerJoin(accountDetail.category, category).fetchJoin()
                .where(
                        accountDetail.account.id.eq(accountId),
                        accountDetail.writeDate.between(from, to)
                )
                .orderBy(
                        accountDetail.writeDate.desc(),
                        accountDetail.createDate.desc()
                )
                .fetch();
    }

    //조회: 월별 지출 통계
    public List<AccountDetailStatsDto> findStatsByAccount(Long accountId, LocalDateTime from, LocalDateTime to) {


        return jpaQueryFactory
                .select(Projections.constructor(AccountDetailStatsDto.class,
                        accountDetail.category.name, accountDetail.amount.sum()))
                .from(accountDetail)
                .where(
                        accountDetail.account.id.eq(accountId),
                        accountDetail.writeDate.between(from, to),
                        accountDetail.detailCd.eq(AccountDetailRole.OUTCOME)
                )
                .groupBy(
                        accountDetail.category
                )
                .fetch();
    }


}
