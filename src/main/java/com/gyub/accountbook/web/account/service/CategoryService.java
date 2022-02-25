package com.gyub.accountbook.web.account.service;


import com.gyub.accountbook.global.dto.account.CategoryDto;
import com.gyub.accountbook.global.exception.ErrorCode;
import com.gyub.accountbook.global.exception.custom.InvalidValueException;
import com.gyub.accountbook.web.account.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    //조회
    public CategoryDto findOne(Long categoryId){
        return CategoryDto.from(
                categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new InvalidValueException("categoryId : " + categoryId, ErrorCode.INVALID_VALUE)
        ));
    }

    public List<CategoryDto> findAll(){
        return categoryRepository.findAll().stream()
                .map(category -> CategoryDto.from(category))
                .collect(Collectors.toList());
    }
}
