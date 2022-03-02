package com.gyub.accountbook.web.sharing.repository;


import com.gyub.accountbook.web.account.domain.QAccount;
import com.gyub.accountbook.web.member.domain.QMember;
import com.gyub.accountbook.web.sharing.domain.QSharing;
import com.gyub.accountbook.web.sharing.domain.Sharing;
import com.gyub.accountbook.web.sharing.domain.SharingState;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SharingQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public SharingQueryRepository(final JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    QSharing sharing = new QSharing("sharing");
    QMember fromMember = new QMember("fromMember");
    QMember toMember = new QMember("toMember");
    QAccount account = new QAccount("account");


    //조회: 공유신청 목록
    public List<Sharing> findAllByMemberId(Long memberId){
        return jpaQueryFactory
                .select(sharing)
                .from(sharing).leftJoin(sharing.fromMember, fromMember).fetchJoin()
                .from(sharing).leftJoin(sharing.toMember, toMember).fetchJoin()
                .where(sharing.fromMember.id.eq(memberId).or(sharing.toMember.id.eq(memberId)))
                .fetch();
    }

    //조회: 공유받은 가계부 목록
    public List<Sharing> findMemberAccountByEmail(String email){
        return jpaQueryFactory
                .select(sharing)
                .from(sharing)
                .innerJoin(sharing.toMember, toMember).fetchJoin()
                .innerJoin(sharing.account, account).fetchJoin()
                .where(
                        toMember.deleteFlag.eq('N'),
                        toMember.email.eq(email),
                        account.deleteFlag.eq('N'),
                        sharing.deleteFlag.eq('N'),
                        sharing.sharingState.ne(SharingState.REFUSE)
                )
                .fetch();
    }




}
