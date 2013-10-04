/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * 一个自定义的filter，
 * 必须包含authenticationManager,accessDecisionManager,securityMetadataSource三个属性
 * 
 * @author wy
 * @version v 0.1 2013-9-24 下午11:04:28 wy Exp $
 */
public class EasySecurityFilter extends AbstractSecurityInterceptor implements Filter {

    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    /**
     * Method that is actually called by the filter chain. Simply delegates to
     * the {@link #invoke(FilterInvocation)} method.
     * 
     * @param request, the servlet request
     * @param response, the servlet response
     * @param chain, the filter chain
     * 
     * @throws IOException, if the filter chain fails
     * @throws ServletException,if the filter chain fails
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                                                                                             throws IOException,
                                                                                             ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
    }

    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    public Class<? extends Object> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        }
        finally {
            super.afterInvocation(token, null);
        }
    }

    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource newSource) {
        this.securityMetadataSource = newSource;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}
