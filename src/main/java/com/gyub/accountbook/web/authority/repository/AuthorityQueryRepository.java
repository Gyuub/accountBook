package com.gyub.accountbook.web.authority.repository;


import com.gyub.accountbook.web.account.domain.QAccount;
import com.gyub.accountbook.web.authority.domain.Authority;
import com.gyub.accountbook.web.authority.domain.QAuthority;
import com.gyub.accountbook.web.authority.domain.Role;
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


    public List<Authority> findByMembers(Long memberId){
        return jpaQueryFactory
                .select(authority)
                .from(authority)
                .innerJoin(authority.account, account).fetchJoin()
                .where(
                        authority.member.id.eq(memberId),
                        account.deleteFlag.eq('N')
                )
                .fetch();
    }

    public List<Authority> findByMemberAndAccount(Long memberId, Long accountId){
        return jpaQueryFactory
                .select(authority)
                .from(authority)
                .where(
                        authority.member.id.eq(memberId)
                        ,authority.account.id.eq(accountId)
                )
                .fetch();
    }

    public List<Authority> findByMemberAndRole(Long memberId, Role role){
        return jpaQueryFactory
                .select(authority)
                .from(authority)
                .where(
                        authority.member.id.eq(memberId)
                        ,authority.role.eq(role)
                )
                .fetch();
    }




}
