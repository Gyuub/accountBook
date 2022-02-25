package com.gyub.accountbook.web.account.repository;


import com.gyub.accountbook.web.account.domain.AccountDetail;
import com.gyub.accountbook.web.account.domain.QAccountDetail;
import com.gyub.accountbook.web.account.domain.QCategory;
import com.gyub.accountbook.web.member.domain.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class AccountQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public AccountQueryRepository(final JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }


    QAccountDetail accountDetail = new QAccountDetail("accountDetail");
    QCategory category = new QCategory("category");
    QMember member = new QMember("member");


    //조회 : 가계부
    public List<AccountDetail> findByAccounts(Long accountId){
        return jpaQueryFactory
                .select(accountDetail)
                .from(accountDetail)
                .leftJoin(accountDetail.category, category).fetchJoin()
                .leftJoin(accountDetail.member, member).fetchJoin()
                .where(
                        accountDetail.account.id.eq(accountId))
                .fetch();
    }






}
