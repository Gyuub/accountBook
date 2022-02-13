package com.gyub.accountbook.web.member.service;

import com.gyub.accountbook.global.dto.member.LoginMemberDto;
import com.gyub.accountbook.global.dto.member.TokenMemberDto;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //==조회==//
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseGet(() -> new Member() );
    }

    @Transactional
    public void join(Member member) {
        //중복체크
        validateDuplicateMember(member);

        //암호화
        member.changePassword(passwordEncoder.encode(member.getPassword()));

        memberRepository.save(member);
    }

    @Transactional
    public void modify(Long id, String nickname, String password){
        Member member = findOne(id);
        validateMatchePassword(password, member);

        member.update(nickname);
    }


    private void validateMatchePassword(String password, Member member){
        if(!passwordEncoder.matches(password, member.getPassword())){
            throw new IllegalArgumentException("기존 비밀번호가 다릅니다.");
        }
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByNickname(member.getNickname());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
