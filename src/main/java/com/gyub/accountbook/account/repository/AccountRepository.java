package com.gyub.accountbook.account.repository;

import com.gyub.accountbook.account.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class AccountRepository {
    private final EntityManager em;

    public Long save(Account account){
        em.persist(account);
        return account.getId();
    }

    public Account findOne(Long id){
        return em.find(Account.class, id);
    }
}
