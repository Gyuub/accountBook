package com.gyub.accountbook.web.account.repository;


import com.gyub.accountbook.web.account.domain.*;
import com.gyub.accountbook.web.authority.domain.QAuthority;
import com.gyub.accountbook.web.member.domain.QMember;
import com.gyub.accountbook.web.sharing.domain.QSharing;
import com.gyub.accountbook.web.sharing.domain.Sharing;
import com.gyub.accountbook.web.sharing.domain.SharingState;
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


    QAccount account = new QAccount("account");
    QAuthority authority = new QAuthority("authority");
    QMember member = new QMember("member");
    QSharing sharing = new QSharing("sharing");


    //조회 : 가계부
    public List<Account> findAccountAuthorityByCreateId(String email){
        return jpaQueryFactory
                .select(account)
                .from(account)
                .innerJoin(account.accountAuthorities, authority).fetchJoin()
                .innerJoin(authority.member, member).fetchJoin()
                .where(
                        account.createId.eq(email)
                )
                .fetch();
    }


}
