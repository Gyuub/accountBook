package com.gyub.accountbook.web.sharing.domain;

import com.gyub.accountbook.global.domain.BaseEntity;
import com.gyub.accountbook.web.account.domain.Account;
import com.gyub.accountbook.web.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor
public class Sharing extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "sharing_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Member.class)
    @JoinColumn(name = "from_member_id")
    private Member fromMember;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Member.class)
    @JoinColumn(name = "to_member_id")
    private Member toMember;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Account.class)
    @JoinColumn(name = "account_id")
    private Account account;

    @Enumerated(EnumType.STRING)
    private SharingState sharingState;

    @Builder
    public Sharing(Long id, Member fromMember, Member toMember, Account account, SharingState sharingState) {
        this.id = id;
        this.fromMember = fromMember;
        this.toMember = toMember;
        this.account = account;
        this.sharingState = sharingState;
    }

    //==비즈니스 로직==//
    public void delete(){
        this.deleteFlag = 'Y';
    }

    public void reply(SharingState sharingState){
        this.sharingState = sharingState;
    }




}
