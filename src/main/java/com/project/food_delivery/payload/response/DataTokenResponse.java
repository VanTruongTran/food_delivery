package com.project.food_delivery.payload.response;

public class DataTokenResponse {
    private String token;
    private String refreshToken;

    /* CONSTRUCTORS */
    public DataTokenResponse(){

    }

    /* GETTERS & SETTERS */
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
