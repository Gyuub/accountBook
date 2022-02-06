package com.gyub.accountbook.web.authority.service;

import com.gyub.accountbook.web.authority.domain.Authority;
import com.gyub.accountbook.web.authority.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    //==조회==//
    public Authority findOne(Long id){
        return authorityRepository.findById(id)
                .orElseGet(() -> new Authority());
    }

    public List<Authority> findByMemeber(Long memberId){
        return authorityRepository.findByMemeber(memberId);
    }
}
