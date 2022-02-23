package com.gyub.accountbook.web.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "member_role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRole {
    @Id
    @Column(name = "authority_name", length = 20)
    private String authorityName;

}
