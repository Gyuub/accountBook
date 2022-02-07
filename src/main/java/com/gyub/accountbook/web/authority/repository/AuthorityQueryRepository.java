package com.gyub.accountbook.web.authority.repository;


import com.gyub.accountbook.web.account.domain.QAccount;
import com.gyub.accountbook.web.authority.domain.Authority;
import com.gyub.accountbook.web.authority.domain.QAuthority;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorityQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public AuthorityQueryRepository(final JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    QAuthority authority = new QAuthority("authority");
    QAccount account = new QAccount("account");

    public List<Authority> test(Long memberId){
        return jpaQueryFactory
                .select(authority).select(authority)
                .from(authority)
                .innerJoin(authority.account, account).fetchJoin()
                .where(authority.member.id.eq(memberId))
                .fetch();
    }



}
