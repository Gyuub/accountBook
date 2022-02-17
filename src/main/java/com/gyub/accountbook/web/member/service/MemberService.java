package com.gyub.accountbook.web.member.service;

import com.gyub.accountbook.global.dto.member.MemberDto;
import com.gyub.accountbook.global.exception.custom.EmailDuplicateException;
import com.gyub.accountbook.global.exception.ErrorCode;
import com.gyub.accountbook.global.exception.custom.MemberNotFoundException;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.member.domain.MemberAuthority;
import com.gyub.accountbook.web.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //==조회==//
    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("memberId : " + memberId, ErrorCode.MEMBER_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public MemberDto getMemberInfo(String email) {
        return MemberDto.from(
                memberRepository.findOneByEmail(email)
                        .orElseThrow(() -> new MemberNotFoundException("email : " + email, ErrorCode.MEMBER_NOT_FOUND))
        );
    }

    @Transactional
    public MemberDto save(Member member) {
        //중복체크
        validateDuplicateMember(member);

        //권한설정
        MemberAuthority authority = MemberAuthority.builder()
                .authorityName("ROLE_USER")
                .build();
        member.addAuthority(authority);
        member.changePassword(passwordEncoder.encode(member.getPassword()));

        return MemberDto.from(memberRepository.save(member));
    }

    @Transactional
    public void update(Long id, String nickname, String password) {
        Member member = memberRepository.findById(id).orElse(null);
        validateMatchePassword(password, member);
        member.update(nickname);
    }


    private void validateMatchePassword(String password, Member member) {
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("기존 비밀번호가 다릅니다.");
        }
    }

    private void validateDuplicateMember(Member member) {
        if (memberRepository.findOneByEmail(member.getEmail()).orElse(null) != null) {
            throw new EmailDuplicateException("중복된 이메일 정보입니다.", ErrorCode.EMAIL_DUPLICATION);
        }
    }
}
