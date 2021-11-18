package com.practikum.naumen.configs;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/admin");
        } else if (roles.contains("ROLE_STAFF")) {
            httpServletResponse.sendRedirect("/staff-request");
        }
//        else if (!roles.contains("ROLE_STAFF") && !roles.contains("ROLE_ADMIN")) {
//            httpServletResponse.sendRedirect("/registration");
//        }


    }

//    @ExceptionHandler(AccessDeniedException.class)
//    public void handleError(HttpServletResponse response) throws IOException {
//        response.sendRedirect("/registration");
//    }
}
