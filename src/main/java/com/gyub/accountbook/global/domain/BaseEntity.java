package com.gyub.accountbook.global.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "create_date")
    protected LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "modify_date")
    protected LocalDateTime modifyDate;


    @Column(name = "delete_yn",length = 1)
    protected char deleteFlag;

    @PrePersist
    public void prePersist() {
        this.deleteFlag = 'N';
    }

}
