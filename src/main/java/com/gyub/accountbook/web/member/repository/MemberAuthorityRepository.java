package com.gyub.accountbook.web.member.repository;

import com.gyub.accountbook.web.member.domain.MemberAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberAuthorityRepository extends JpaRepository<MemberAuthority, Long> {
}
