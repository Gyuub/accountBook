package com.gyub.accountbook.web.member.repository;


import com.gyub.accountbook.web.account.domain.QAccount;
import com.gyub.accountbook.web.authority.domain.Authority;
import com.gyub.accountbook.web.authority.domain.QAuthority;
import com.gyub.accountbook.web.authority.domain.Role;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.member.domain.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public MemberQueryRepository(final JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    QMember member = QMember.member;
    QAuthority authority = QAuthority.authority;
    QAccount account = QAccount.account;


    public List<Member> findByEmail(String email){
        return jpaQueryFactory
                .select(member)
                .from(member)
                .leftJoin(authority ).fetchJoin()
                    .on(authority.member.eq(member))
                .innerJoin(account ).fetchJoin()
                    .on(authority.account.eq(account), account.deleteFlag.eq('N'))
                .where(member.email.eq(email))
                .fetch();
    }
}
