package com.gyub.accountbook.web.member.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "member_role")
public class MemberAuthority {
    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;

}
