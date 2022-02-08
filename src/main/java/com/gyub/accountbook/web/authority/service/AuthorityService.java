package com.gyub.accountbook.web.authority.service;

import com.gyub.accountbook.web.authority.domain.Authority;
import com.gyub.accountbook.web.authority.domain.Role;
import com.gyub.accountbook.web.authority.repository.AuthorityQueryRepository;
import com.gyub.accountbook.web.authority.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityRepository authorityRepository;
    private final AuthorityQueryRepository authorityQueryRepository;

    public Authority findOne(Long id){
        return authorityRepository.findById(id)
                .orElseGet(() -> new Authority());
    }

    public List<Authority> findByMemeber(Long memberId){
        return authorityQueryRepository.findByMembers(memberId);
    }

    public List<Authority> findByMemberAndOwner(Long memberId, Role role){
        return authorityQueryRepository.findByMemberAndRole(memberId, role);
    }

    //====//
    public boolean isAuthorityOwner(Long memberId, Long accountId){
        return isOwner(memberId, accountId);
    }

    public void save(Authority authority){
        validateDuplicate(authority);
        authorityRepository.save(authority);
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
