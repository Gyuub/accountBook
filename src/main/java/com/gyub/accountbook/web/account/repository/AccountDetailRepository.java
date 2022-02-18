package com.gyub.accountbook.web.account.repository;

import com.gyub.accountbook.web.account.domain.AccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDetailRepository extends JpaRepository<AccountDetail, Long> {
}
