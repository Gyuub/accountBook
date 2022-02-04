package com.gyub.accountbook.account.repository;

import com.gyub.accountbook.account.domain.detail.AccountDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public interface AccountDetailRepository extends JpaRepository<AccountDetail, Long> {
}
