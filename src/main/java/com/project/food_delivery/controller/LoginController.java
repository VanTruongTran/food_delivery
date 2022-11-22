package com.project.food_delivery.controller;

import com.project.food_delivery.payload.request.SigninRequest;
import com.project.food_delivery.payload.response.DataResponse;
import com.project.food_delivery.service.LoginService;
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
    LoginService loginService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("")
    public ResponseEntity<DataResponse> signin(@RequestBody SigninRequest signinRequest) {
//        boolean success = loginService.checkLogin(signinRequest.getEmail(), signinRequest.getPassword());

        UsernamePasswordAuthenticationToken authenticationRequest
                = new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(authenticationRequest);

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);


        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test() {
        return "TEST";
    }
}
