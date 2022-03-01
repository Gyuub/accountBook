package com.gyub.accountbook.global.configuration.interceptor;

import com.gyub.accountbook.global.dto.authority.AuthorityDto;
import com.gyub.accountbook.global.exception.ErrorCode;
import com.gyub.accountbook.global.exception.custom.AccountUnauthorizedException;
import com.gyub.accountbook.global.exception.custom.MemberNotFoundException;
import com.gyub.accountbook.global.util.SecurityUtil;
import com.gyub.accountbook.web.authority.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AuthorityInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(AuthorityInterceptor.class);
    private final AuthorityService authorityService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("AuthorityInterceptor =========> 권한 확인");

        //==로그인정보조회==//
        String email = SecurityUtil.getCurrentUserEmail();

        //==권한내 가계부 목록==//
        List<AuthorityDto> myAccounts = authorityService.getMyAccountAuthorities();

        //==접근하려는 가계부==//
        Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long accountId = Long.parseLong((String)pathVariables.get("accountid"));


        myAccounts.stream()
                .filter(dto -> dto.getAccount().getId().equals(accountId))
                .findAny()
                .orElseThrow(()-> new AccountUnauthorizedException("가계부에 대한 접근을 할 수 없습니다."
                        , ErrorCode.ACCOUNT_UNAUTHORIZED));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
