package com.gyub.accountbook.web.account.repository;

import com.gyub.accountbook.web.account.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
