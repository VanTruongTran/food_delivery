package com.project.food_delivery.jwt;

import com.google.gson.Gson;
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
import java.util.Map;

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
                String json = jwtTokenHelper.decodeToken(token);

                Gson gson = new Gson();
                Map<String, Object> objectData = gson.fromJson(json, Map.class);

                System.out.println("CHECK TYPE:" + objectData.get("type"));

                if (StringUtils.hasText(objectData.get("type").toString())
                        && !objectData.get("type").toString().equals("refresh")) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("", "", new ArrayList<>());
                    SecurityContext securityContext = SecurityContextHolder.getContext();
                    securityContext.setAuthentication(authenticationToken);
                }
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
