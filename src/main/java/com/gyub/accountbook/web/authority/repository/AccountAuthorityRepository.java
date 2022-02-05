package com.gyub.accountbook.web.authority.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AccountAuthorityRepository {
    @PersistenceContext
    private EntityManager em;
}
