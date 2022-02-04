package com.gyub.accountbook.account.repository;

import com.gyub.accountbook.account.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
   List<Account> findByName(String name);
}
