package com.gyub.accountbook.web.account.repository;

import com.gyub.accountbook.web.account.domain.AccountDetail;
import com.gyub.accountbook.web.account.domain.QAccountDetail;
import com.gyub.accountbook.web.account.domain.QCategory;
import com.gyub.accountbook.web.member.domain.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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

    List<AccountDetail> findAllByDateMonth(LocalDateTime from, LocalDateTime to){
        return jpaQueryFactory
                .select(accountDetail)
                .from(accountDetail)
                .innerJoin(accountDetail.member, member).fetchJoin()
                .innerJoin(accountDetail.category, category).fetchJoin()
                .where(
                        accountDetail.writeDate.between(from, to)
                ).fetch();
    }

}
