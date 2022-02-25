package com.gyub.accountbook.web.account.controller;


import com.gyub.accountbook.global.dto.ResultListResponse;
import com.gyub.accountbook.global.dto.ResultResponse;
import com.gyub.accountbook.global.dto.account.AccountDetailDto;
import com.gyub.accountbook.global.dto.account.CategoryDto;
import com.gyub.accountbook.web.account.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category/{categoryid}")
    public ResponseEntity<ResultResponse> findCategoryById(
            @PathVariable("categoryid") Long categoryId
    ){
        return ResponseEntity.ok()
                .body(new ResultResponse(categoryService.findOne(categoryId), ""));
    }

    @GetMapping("/category")
    public ResponseEntity<ResultListResponse> findAllCategory(
    ){
        List<CategoryDto> result = categoryService.findAll();
        return ResponseEntity.ok()
                .body(new ResultListResponse(result, result.size(), ""));
    }



}
