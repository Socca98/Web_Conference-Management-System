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
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (securityService.isPrehandle() || securityService.checkToken())
            return super.preHandle(request, response, handler);
        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\" : \"Unauthorized!\" }");
        return false;
    }
}
