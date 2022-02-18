package com.gyub.accountbook.web.sharing.service;

import com.gyub.accountbook.global.dto.member.MemberDto;
import com.gyub.accountbook.global.dto.sharing.SharingDto;
import com.gyub.accountbook.global.exception.ErrorCode;
import com.gyub.accountbook.global.exception.custom.InvalidValueException;
import com.gyub.accountbook.global.util.SecurityUtil;
import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.account.service.AccountService;
import com.gyub.accountbook.web.authority.domain.Role;
import com.gyub.accountbook.web.authority.service.AuthorityService;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.member.service.MemberService;
import com.gyub.accountbook.web.sharing.domain.Sharing;
import com.gyub.accountbook.web.sharing.domain.SharingState;
import com.gyub.accountbook.web.sharing.repository.SharingQueryRepository;
import com.gyub.accountbook.web.sharing.repository.SharingRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SharingService {
    private static final Logger logger = LoggerFactory.getLogger(SharingService.class);

    private final SharingRepository sharingRepository;
    private final SharingQueryRepository sharingQueryRepository;

    private final MemberService memberService;
    private final AccountService accountService;
    private final AuthorityService authorityService;

    @Transactional(readOnly = true)
    public Sharing findOne(Long sharingId) {
        return sharingRepository.findById(sharingId)
                .orElseGet(() -> new Sharing());
    }

    @Transactional(readOnly = true)
    public List<SharingDto> findByAll() {
        String email = SecurityUtil.getCurrentUserEmail();
        MemberDto memberInfo = memberService.getMemberInfo(email);
        return sharingQueryRepository.findAllByMemberId(memberInfo.getId())
                .stream()
                .map(sharing -> SharingDto.from(sharing))
                .collect(Collectors.toList());
    }

    //공유 신청
    @Transactional
    public SharingDto save(Long fromMemberId, Long toMemberId, Long accountId) {
        Sharing sharing = Sharing.builder()
                .toMember(Member.builder().id(toMemberId).build())
                .fromMember(Member.builder().id(fromMemberId).build())
                .account(Account.builder().id(accountId).build())
                .sharingState(SharingState.INVITE)
                .build();
        return SharingDto.from(
                sharingRepository.save(sharing)
        );
    }

    //공유 결과 응답
    @Transactional
    public SharingDto replyInvite(Long sharingId, SharingState sharingState) {
        //유저 정보 조회
        String email = SecurityUtil.getCurrentUserEmail();

        //공유 정보 조회
        Sharing findSharing = sharingRepository.findOneToMemberById(sharingId)
                .orElseThrow(() -> new InvalidValueException("sharingId : " + sharingId, ErrorCode.INVALID_VALUE));

        validateMatcheMember(email, findSharing.getToMember());

        //응답
        findSharing.changeState(sharingState);

        if (SharingState.ACCEPT.equals(sharingState)) {
            authorityService.save(findSharing.getToMember(), findSharing.getAccount(), Role.GUEST);
        } else {
            findSharing.delete();
        }
        return SharingDto.from(findSharing);
    }

    //공유 신청 취소
    @Transactional
    public void cancelInvite(Long sharingId) {
        Sharing sharing = sharingRepository.findById(sharingId)
                .orElseThrow(() -> new InvalidValueException("공유 아이디가 잘못 되었음 : ."+ sharingId
                        , ErrorCode.INVALID_VALUE));

        validateCurrentStateInvite(sharing.getSharingState());


        sharingRepository.delete(sharing);
    }

    private void validateMatcheMember(String email, Member member) {
        if (!email.equals(member.getEmail())) {
            throw new InvalidValueException("email : " + email, ErrorCode.INVALID_VALUE);
        }
    }

    private void validateCurrentStateInvite(SharingState sharingState){
        if(!SharingState.INVITE.equals(sharingState)){
            throw new InvalidValueException("삭제 할 수 없는 상태 : "+ sharingState, ErrorCode.INVALID_VALUE);
        }
    }
}
