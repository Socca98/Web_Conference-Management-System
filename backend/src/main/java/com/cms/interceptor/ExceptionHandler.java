package com.cms.interceptor;

import com.cms.exception.IssException;
import com.cms.exception.ResponseException;
import org.springframework.core.annotation.Order;
import org.springframework.core.Ordered;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 5500)
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(IssException.class)
    public ResponseEntity<ResponseException> handleIssException(IssException ex, WebRequest webRequest) {
        return new ResponseEntity<>(new ResponseException().message(ex.getMessage(), ex.getStatus()), ex.getStatus()) ;
    }
}
