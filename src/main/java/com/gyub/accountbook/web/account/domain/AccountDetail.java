package com.gyub.accountbook.web.account.domain;

import com.gyub.accountbook.global.domain.BaseEntity;
import com.gyub.accountbook.web.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "delete_yn = 'N'")
public class AccountDetail extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "account_detail_id")
    private Long id;

    private String title;
    private String contents;
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = true)
    private Category category;

    //==비즈니스 로직==//
    public void addMember(Member member){
        this.member = member;
    }
    public void addCatecory(Category category){
        this.category = category;
    }
    public void update(String title, String contents, Integer amount, Category category){
        this.title = title;
        this.contents = contents;
        this.amount = amount;
        this.category = category;
    }

    public void delete(){
        this.deleteFlag = 'Y';
    }

    @Override
    public String toString() {
        return "AccountDetail{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", amount=" + amount +
                '}';
    }
}
