package com.gyub.accountbook.web.sharing.service;

import com.gyub.accountbook.global.dto.member.MemberDto;
import com.gyub.accountbook.global.dto.sharing.SharingAccountDto;
import com.gyub.accountbook.global.dto.sharing.SharingDto;
import com.gyub.accountbook.global.exception.ErrorCode;
import com.gyub.accountbook.global.exception.custom.InvalidValueException;
import com.gyub.accountbook.global.util.SecurityUtil;
import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.account.repository.AccountRepository;
import com.gyub.accountbook.web.account.service.AccountService;
import com.gyub.accountbook.web.authority.domain.Role;
import com.gyub.accountbook.web.authority.service.AuthorityService;
import com.gyub.accountbook.web.member.domain.Member;
import com.gyub.accountbook.web.member.repository.MemberRepository;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SharingService {
    private static final Logger logger = LoggerFactory.getLogger(SharingService.class);

    private final MemberRepository memberRepository;
    private final AccountRepository accountRepository;
    private final SharingRepository sharingRepository;
    private final SharingQueryRepository sharingQueryRepository;

    private final AuthorityService authorityService;


    public Sharing findOne(Long sharingId) {
        return sharingRepository.findById(sharingId)
                .orElseGet(() -> new Sharing());
    }

    public List<SharingDto> findByAll() {
        String email = SecurityUtil.getCurrentUserEmail();
        Member member = memberRepository.findOneByEmail(email)
                .orElseThrow(() -> new InvalidValueException("", ErrorCode.MEMBER_NOT_FOUND));
        return sharingQueryRepository.findAllByMemberId(member.getId())
                .stream()
                .map(sharing -> SharingDto.from(sharing))
                .collect(Collectors.toList());
    }

    public List<SharingAccountDto> findMemberAccountByEmail() {
        String email = SecurityUtil.getCurrentUserEmail();
        return sharingQueryRepository.findMemberAccountByEmail(email)
                .stream()
                .map(sharing -> SharingAccountDto.from(sharing))
                .collect(Collectors.toList());
    }

    //공유 신청
    @Transactional
    public SharingDto save(String toMemberEmail, Long accountId) {
        //유저 정보 조회
        String fromMemberEmail = SecurityUtil.getCurrentUserEmail();

        //본인 가계부 검사
        validateMyAccount(fromMemberEmail, toMemberEmail);

        Member fromMember = memberRepository.findOneByEmail(fromMemberEmail)
                .orElseThrow(() -> new InvalidValueException("", ErrorCode.MEMBER_NOT_FOUND));

        Member toMember = memberRepository.findOneByEmail(toMemberEmail)
                .orElseThrow(() -> new InvalidValueException("신청 대상이 찾을 수 없습니다.", ErrorCode.MEMBER_NOT_FOUND));

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new InvalidValueException("가계부를 다시 선택 후 신청해 주세요.", ErrorCode.INVALID_VALUE));

        //중복 신청인지 검사
        validateDuplicateSharing(fromMember, toMember, account);

        //공유정보
        Sharing sharing = Sharing.builder()
                .fromMember(fromMember)
                .toMember(toMember)
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
                .orElseThrow(() -> new InvalidValueException("공유 아이디가 잘못 되었음 : ." + sharingId
                        , ErrorCode.INVALID_VALUE));

        validateCurrentStateInvite(sharing.getSharingState());


        sharingRepository.delete(sharing);
    }

    private void validateMatcheMember(String email, Member member) {
        if (!email.equals(member.getEmail())) {
            throw new InvalidValueException("email : " + email, ErrorCode.INVALID_VALUE);
        }
    }

    private void validateCurrentStateInvite(SharingState sharingState) {
        if (!SharingState.INVITE.equals(sharingState)) {
            throw new InvalidValueException("삭제 할 수 없는 상태 : " + sharingState, ErrorCode.INVALID_VALUE);
        }
    }

    private void validateMyAccount(String fromEmail, String toEamil) {
        if (fromEmail.equals(toEamil)) {
            throw new InvalidValueException("공유 하려는 이메일 주소를 확인해 주세요 toEamil : " + toEamil, ErrorCode.INVALID_VALUE);
        }
    }

    private void validateDuplicateSharing(Member fromMember, Member toMember, Account account) {
        if (!sharingRepository.findOneByFromMemberAndToMemberAndAccount(
                fromMember, toMember, account).isEmpty()){
            throw new InvalidValueException("이미 공유중인 가계부 입니다.", ErrorCode.INVALID_VALUE);
        }
    }
}
