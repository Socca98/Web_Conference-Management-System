package com.cms.interceptor;

import com.cms.services.SecurityService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoggingInterceptor extends HandlerInterceptorAdapter {
    Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    @Autowired
    SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logRequest(request);
        return true;
    }

    private void logRequest(HttpServletRequest request) throws IOException {
        logger.debug("------------Incoming request------------\nSessionId:{}\nMethod:{}\nPath:{}\n\n----------------------------------------",
                request.getSession().getId(),
                request.getMethod(),
                request.getServletPath());
    }

}
