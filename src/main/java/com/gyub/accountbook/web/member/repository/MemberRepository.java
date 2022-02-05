package com.gyub.accountbook.web.member.repository;

import com.gyub.accountbook.web.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByNickname(String nickname);
}
