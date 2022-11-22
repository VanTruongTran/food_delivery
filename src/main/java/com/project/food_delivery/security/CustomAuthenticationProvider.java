package com.project.food_delivery.security;

import com.project.food_delivery.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

//nhận vào username và password từ người dùng để tiến hành xử lý
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    LoginService loginService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        boolean success = loginService.checkLogin(email, password);

        if (success) {
            return new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
