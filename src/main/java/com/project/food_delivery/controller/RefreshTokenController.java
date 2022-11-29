package com.project.food_delivery.controller;

import com.google.gson.Gson;
import com.project.food_delivery.jwt.JwtTokenHelper;
import com.project.food_delivery.payload.response.DataResponse;
import com.project.food_delivery.payload.response.DataTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/refresh-token")
public class RefreshTokenController {
    //thời gian hết hạn của token (milisec)
    private long expiredTime = 8 * 60 * 60 * 1000;

    //thời gian hết hạn của refresh token (milisec)
    private long refreshExpiredTime = 80 * 60 * 60 * 1000;

    @Autowired
    JwtTokenHelper jwtTokenHelper;

    @PostMapping("")
    public ResponseEntity<?> index(@RequestParam("token") String token) {
        DataResponse dataResponse = new DataResponse();
        if (jwtTokenHelper.validateToken(token)) {
            String json = jwtTokenHelper.decodeToken(token);

            Gson gson = new Gson();
            Map<String, Object> objectData = gson.fromJson(json, Map.class);

            System.out.println("CHECK TYPE:" + objectData.get("type"));

            if (StringUtils.hasText(objectData.get("type").toString())
                    && objectData.get("type").toString().equals("refresh")) {


                //mã hóa chuỗi email thành chuỗi token
                String authenToken = jwtTokenHelper.generateToken(objectData.get("email").toString(), "authen", expiredTime);

                //tạo refresh token
                String refreshToken = jwtTokenHelper.generateToken(objectData.get("email").toString(), "refresh", refreshExpiredTime);

                DataTokenResponse dataTokenResponse = new DataTokenResponse();
                dataTokenResponse.setToken(authenToken);
                dataTokenResponse.setRefreshToken(refreshToken);

                dataResponse.setData(dataTokenResponse);
            }
        }

        dataResponse.setSuccess(true);
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDescription("");

        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
