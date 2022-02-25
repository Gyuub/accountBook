package com.gyub.accountbook.web.sharing.domain;

import com.gyub.accountbook.global.domain.BaseEntity;
import com.gyub.accountbook.global.util.SecurityUtil;
import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sharing extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "sharing_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_member_id")
    private Member fromMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_member_id")
    private Member toMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private SharingState sharingState;


    //==비즈니스 로직==//
    public void delete(){
        this.modifyId = SecurityUtil.getCurrentUserEmail();
        this.deleteFlag = 'Y';
    }

    public void changeState(SharingState sharingState){
        this.sharingState = sharingState;
    }
}
