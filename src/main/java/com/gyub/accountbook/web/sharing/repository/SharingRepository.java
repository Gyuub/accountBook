package com.gyub.accountbook.web.sharing.repository;

import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.sharing.domain.Sharing;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SharingRepository extends JpaRepository<Sharing, Long> {

    @EntityGraph(attributePaths = {"toMember", "account"})
    Optional<Sharing> findOneToMemberById(Long sharingId);

    Optional<Sharing> findOneByFromMemberAndToMemberAndAccount(Member fromMember, Member toMember, Account account);
}
