package com.cms.exception;

public class ResponseException {
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
}
