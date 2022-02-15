package com.gyub.accountbook.web.sharing.service;

import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.account.service.AccountService;
import com.gyub.accountbook.web.authority.domain.Role;
import com.gyub.accountbook.web.authority.service.AuthorityService;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.member.service.MemberService;
import com.gyub.accountbook.web.sharing.domain.Sharing;
import com.gyub.accountbook.web.sharing.domain.SharingState;
import com.gyub.accountbook.web.sharing.repository.SharingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SharingService {

    private final SharingRepository sharingRepository;

    private final MemberService memberService;
    private final AccountService accountService;
    private final AuthorityService authorityService;

    @Transactional(readOnly = true)
    public Sharing findOne(Long sharingId) {
        return sharingRepository.findById(sharingId)
                .orElseGet(() -> new Sharing());
    }
    @Transactional(readOnly = true)
    public List<Sharing> findByAll() {
        return sharingRepository.findAll();
    }

    //공유 신청
    @Transactional
    public Long invite(Long fromMemberId, Long toMemberId, Long accountId) {
        Sharing sharing = Sharing.builder()
                .toMember(Member.builder().id(toMemberId).build())
                .fromMember(Member.builder().id(fromMemberId).build())
                .account(Account.builder().id(accountId).build())
                .sharingState(SharingState.INVITE)
                .build();

        sharingRepository.save(sharing);
        return sharing.getId();
    }

    //공유 결과 응답
    @Transactional
    public void replyInvite(Long sharingId, SharingState sharingState) {
        Sharing findSharing = findOne(sharingId);
        findSharing.reply(sharingState);

        if (SharingState.ACCEPT.equals(sharingState)) {
            //엔티티 조회
            Member toMember = memberService.findOne(findSharing.getToMember().getId());
            Account account = accountService.findOne(findSharing.getAccount().getId());

            authorityService.save(toMember, account, Role.GUEST);
        } else {
            findSharing.delete();
        }
    }

    //공유 관계 삭제
    @Transactional
    public void delete(Long sharingId) {
        Sharing sharing = sharingRepository.findById(sharingId).orElse(null);
        sharing.delete();
    }

    //공유 신청 취소
    @Transactional
    public void cancelInvite(Long sharingId) {
        Sharing sharing = sharingRepository.findById(sharingId).orElse(null);
        sharingRepository.delete(sharing);
    }

}
