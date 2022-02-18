package com.gyub.accountbook.web.sharing.repository;


import com.gyub.accountbook.web.member.domain.QMember;
import com.gyub.accountbook.web.sharing.domain.QSharing;
import com.gyub.accountbook.web.sharing.domain.Sharing;
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


    //조회 : 가계부
    public List<Sharing> findAllByMemberId(Long memberId){
        return jpaQueryFactory
                .select(sharing)
                .from(sharing).leftJoin(sharing.fromMember, fromMember).fetchJoin()
                .from(sharing).leftJoin(sharing.toMember, toMember).fetchJoin()
                .where(sharing.fromMember.id.eq(memberId).or(sharing.toMember.id.eq(memberId)))
                .fetch();
    }




}
