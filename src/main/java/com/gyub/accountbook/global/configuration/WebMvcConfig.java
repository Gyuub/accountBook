package com.gyub.accountbook.global.configuration;

import com.gyub.accountbook.global.configuration.interceptor.AuthorityInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    private final AuthorityInterceptor authorityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorityInterceptor)
                .addPathPatterns("/api/v1/account/**"); // 해당 경로에 접근하기 전에 인터셉터가 가로챈다.
                //.excludePathPatterns("/**"); // 해당 경로는 인터셉터가 가로채지 않는다.
    }
}
