package com.gyub.accountbook.web.authority.service;

import com.gyub.accountbook.web.authority.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AuthorityServiceTest {

    @Autowired
    AuthorityService authorityService;

    public void 권한_조회(){

    }

}