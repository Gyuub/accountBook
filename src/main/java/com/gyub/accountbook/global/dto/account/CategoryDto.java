package com.gyub.accountbook.global.dto.account;

import com.gyub.accountbook.web.account.domain.AccountDetailRole;
import com.gyub.accountbook.web.account.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private Integer priority;
    private AccountDetailRole groupId;

    public static CategoryDto from(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .priority(category.getPriority())
                .groupId(category.getGroupId())
                .build();
    }
}
