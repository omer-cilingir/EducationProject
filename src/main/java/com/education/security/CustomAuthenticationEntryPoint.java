package com.education.security;

import com.education.exception.ErrorResponse;
import com.education.util.JsonMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) throws IOException {
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(403);
        res.getWriter().write(JsonMapper.toJsonString(
                ErrorResponse.builder()
                        .code(403)
                        .message("User bulunamadı veya erişim yetkiniz yok")
                        .build()
        ));
    }
}