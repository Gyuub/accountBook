package com.gyub.accountbook.web.authority.repository;

import com.gyub.accountbook.web.authority.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
