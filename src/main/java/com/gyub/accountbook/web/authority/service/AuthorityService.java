package com.gyub.accountbook.web.authority.service;

import com.gyub.accountbook.global.dto.authority.AuthorityDto;
import com.gyub.accountbook.global.exception.ErrorCode;
import com.gyub.accountbook.global.exception.custom.MemberNotFoundException;
import com.gyub.accountbook.global.util.SecurityUtil;
import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.authority.domain.Authority;
import com.gyub.accountbook.web.authority.domain.Role;
import com.gyub.accountbook.web.authority.repository.AuthorityQueryRepository;
import com.gyub.accountbook.web.authority.repository.AuthorityRepository;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthorityService {

    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;
    private final AuthorityQueryRepository authorityQueryRepository;

    public Authority findOne(Long id){
        return authorityRepository.findById(id)
                .orElse(null);
    }

    public List<AuthorityDto> getMyAccountAuthorities(){
        String email = SecurityUtil.getCurrentUserEmail();

        return authorityQueryRepository.findByMembers(email).stream()
                .map(authority -> AuthorityDto.from(authority))
                .collect(Collectors.toList());
    }

    public List<Authority> findByMemberAndOwner(Long memberId, Role role){
        return authorityQueryRepository.findByMemberAndRole(memberId, role);
    }

    @Transactional
    public void save(Member member, Account account, Role role){

        Authority authority = Authority.builder()
                .member(member)
                .account(account)
                .role(role).build();
        validateDuplicate(authority);
        authorityRepository.save(authority);
    }

    //====//
    public boolean isAuthorityOwner(Long memberId, Long accountId){
        return isOwner(memberId, accountId);
    }


    private boolean isOwner(Long memberId, Long accountId){
        List<Authority> authorities = authorityQueryRepository
                .findByMemberAndAccount(memberId, accountId);

        return authorities.stream()
                .anyMatch(authority -> authority.getRole().equals(Role.OWNER));
    }

    private void validateDuplicate(Authority authority){
        List<Authority> authorities = authorityQueryRepository
                .findByMemberAndAccount(authority.getMember().getId()
                                        , authority.getAccount().getId());
        if(!authorities.isEmpty()){
            throw new IllegalStateException("이미 존재하는 가계부입니다.");
        }
    }
}
