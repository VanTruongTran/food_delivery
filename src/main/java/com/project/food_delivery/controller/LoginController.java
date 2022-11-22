package com.project.food_delivery.controller;

import com.project.food_delivery.payload.request.SigninRequest;
import com.project.food_delivery.payload.response.DataResponse;
import com.project.food_delivery.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/signin")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("")
    public ResponseEntity<DataResponse> signin(@RequestBody SigninRequest signinRequest) {
        boolean success = loginService.checkLogin(signinRequest.getEmail(), signinRequest.getPassword());

        DataResponse dataResponse = new DataResponse(success, HttpStatus.OK.value(), "", "");
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
