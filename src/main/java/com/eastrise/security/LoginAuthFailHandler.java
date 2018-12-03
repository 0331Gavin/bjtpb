package com.eastrise.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * create by gzq on 2018/10/31 14:54
 */
public class LoginAuthFailHandler extends SimpleUrlAuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String targetUrl = "/";
        targetUrl += "?" + "error=true";
        super.setDefaultFailureUrl(targetUrl);
        super.onAuthenticationFailure(request, response, exception);
    }
}
