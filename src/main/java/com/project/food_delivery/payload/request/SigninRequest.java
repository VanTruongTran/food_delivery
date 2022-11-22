package com.project.food_delivery.payload.request;

public class SigninRequest {
    private String email;
    private String password;

    /* CONSTRUCTORS */
    public SigninRequest() {

    }

    /* GETTERS & SETTERS */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
