package com.cms.exception;

import org.springframework.http.HttpStatus;

public class IssException extends RuntimeException {

    private HttpStatus status;

    public IssException() {
        super();
    }

    public IssException(String message) {
        super(message);
    }

    public IssException(String message, HttpStatus httpStatus) {
        super(message);
        this.status = httpStatus;
    }

    public IssException(String message, Throwable cause) {
        super(message, cause);
    }

    public IssException(Throwable cause) {
        super(cause);
    }

    protected IssException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
