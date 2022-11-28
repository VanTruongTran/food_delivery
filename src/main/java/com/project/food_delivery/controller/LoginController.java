package com.project.food_delivery.controller;

import com.project.food_delivery.jwt.JwtTokenHelper;
import com.project.food_delivery.payload.request.SigninRequest;
import com.project.food_delivery.payload.response.DataResponse;
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
        String token = jwtTokenHelper.generateToken(signinRequest.getEmail());

        //giải mã token
        String decodeToken = jwtTokenHelper.decodeToken(token);

        DataResponse dataResponse = new DataResponse();
        dataResponse.setSuccess(true);
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setData(token);
        dataResponse.setDescription(decodeToken);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test() {
        return "TEST";
    }
}
