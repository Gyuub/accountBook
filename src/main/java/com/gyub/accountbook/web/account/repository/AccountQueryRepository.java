package com.gyub.accountbook.web.account.repository;


import com.gyub.accountbook.web.account.domain.QAccount;
import com.gyub.accountbook.web.account.domain.AccountDetail;
import com.gyub.accountbook.web.account.domain.detail.QAccountDetail;
import com.gyub.accountbook.web.account.domain.detail.QCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public AccountQueryRepository(final JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    QAccount account = new QAccount("account");
    QAccountDetail accountDetail = new QAccountDetail("accountDetail");
    QCategory category = new QCategory("category");


    //조회 : 가계부
    public List<AccountDetail> findByAccounts(Long accountId){
        return jpaQueryFactory
                .select(accountDetail)
                .from(accountDetail)
                .leftJoin(accountDetail.category, category).fetchJoin()
                .where(
                        accountDetail.account.id.eq(accountId))
                .fetch();
    }




}
