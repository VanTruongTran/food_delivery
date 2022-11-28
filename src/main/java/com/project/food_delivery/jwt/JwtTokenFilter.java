package com.project.food_delivery.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //cắt header và lấy token gửi kèm request
        String token = getTokenFromHeader(request);

        if (token != null) {
            //kiểm tra chuỗi token có phải do hệ thống tạo ra hay không
            if (jwtTokenHelper.validateToken(token)) {
                String email = jwtTokenHelper.decodeToken(token);

                System.out.println(token);
                System.out.println(email);

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, "", new ArrayList<>());
                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        //lấy giá trị chuỗi token từ header key 'Authorization'
        String stringToken = request.getHeader("Authorization");

        //kiểm tra chuỗi token hợp lệ
        if (StringUtils.hasText(stringToken) && stringToken.startsWith("Bearer ")) {
            return stringToken.substring(7);
        }
        return null;
    }
}
