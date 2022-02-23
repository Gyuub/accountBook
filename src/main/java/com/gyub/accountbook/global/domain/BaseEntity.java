package com.gyub.accountbook.global.domain;

import com.gyub.accountbook.global.util.SecurityUtil;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Column(name = "create_id", updatable = false, length = 40)
    protected String createId;

    @CreatedDate
    @Column(name = "create_date", updatable = false)
    protected LocalDateTime createDate;

    @Column(name = "modify_id", length = 40)
    protected String modifyId;

    @LastModifiedDate
    @Column(name = "modify_date")
    protected LocalDateTime modifyDate;


    @Column(name = "delete_yn", length = 1)
    protected Character deleteFlag;

    @PrePersist
    public void prePersist() {
        this.deleteFlag = 'N';
        this.createId = SecurityUtil.getCurrentUserEmail();
    }
    @PreUpdate
    public void PreUpdate() {
        this.modifyId = SecurityUtil.getCurrentUserEmail();
    }
}
