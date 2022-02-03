package com.gyub.accountbook.account.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AccountDetailRepository {
    @PersistenceContext
    private EntityManager em;
}
