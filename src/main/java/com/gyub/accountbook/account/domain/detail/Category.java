package com.gyub.accountbook.account.domain.detail;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "categoty_id")
    private Long id;

    private String name;

}
