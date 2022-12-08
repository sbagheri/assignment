package com.sbaghrei.order.model.base;

public class BaseResponse {
    String message;
    String status;

    public BaseResponse() {
    }

    public BaseResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
