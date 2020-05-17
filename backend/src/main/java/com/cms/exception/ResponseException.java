package com.cms.exception;

import org.springframework.http.HttpStatus;

public class ResponseException {
    private int status;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public ResponseException message(String message) {
        this.setMessage(message);
        return this;
    }

    public ResponseException message(String message, HttpStatus httpStatus) {
        this.setMessage(message);
        this.setStatus(httpStatus);
        return this;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status.value();
    }
}
