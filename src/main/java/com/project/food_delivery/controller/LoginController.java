package com.project.food_delivery.controller;

import com.project.food_delivery.jwt.JwtTokenHelper;
import com.project.food_delivery.payload.request.SigninRequest;
import com.project.food_delivery.payload.response.DataResponse;
import com.project.food_delivery.payload.response.DataTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/signin")
public class LoginController {
    //thời gian hết hạn của token (milisec)
    private long expiredTime = 8 * 60 * 60 * 1000;

    //thời gian hết hạn của refresh token (milisec)
    private long refreshExpiredTime = 80 * 60 * 60 * 1000;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenHelper jwtTokenHelper;

    @PostMapping("")
    public ResponseEntity<?> signin(@RequestBody SigninRequest signinRequest) {
        UsernamePasswordAuthenticationToken authenticationRequest
                = new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationRequest);

        //nếu authentication = null thì server sẽ trả về lỗi 403 và không chạy code bên dưới

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        //mã hóa chuỗi email thành chuỗi token
        String token = jwtTokenHelper.generateToken(signinRequest.getEmail(),"authen", expiredTime);

        //tạo refresh token
        String refreshToken = jwtTokenHelper.generateToken(signinRequest.getEmail(),"refresh", refreshExpiredTime);

        DataTokenResponse dataTokenResponse = new DataTokenResponse();
        dataTokenResponse.setToken(token);
        dataTokenResponse.setRefreshToken(refreshToken);

        DataResponse dataResponse = new DataResponse();
        dataResponse.setSuccess(true);
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setData(dataTokenResponse);
        dataResponse.setDescription("");
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test() {
        return "TEST";
    }
}
