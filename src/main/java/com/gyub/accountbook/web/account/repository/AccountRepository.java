package com.gyub.accountbook.web.account.repository;

import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.member.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
   List<Account> findByName(String name);

   @EntityGraph(attributePaths = {"accountAuthorities", "accountAuthorities.member"} )
   List<Account> findByCreateId(String createId);
}
