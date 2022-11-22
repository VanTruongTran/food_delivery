package com.project.food_delivery.payload.response;

public class DataResponse {
    private boolean success;
    private int status;
    private Object data;
    private String description;

    /* CONSTRUCTORS */
    public DataResponse() {

    }

    public DataResponse(boolean success, int status, Object data, String description) {
        this.success = success;
        this.status = status;
        this.data = data;
        this.description = description;
    }

    /* GETTERS & SETTERS */
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
