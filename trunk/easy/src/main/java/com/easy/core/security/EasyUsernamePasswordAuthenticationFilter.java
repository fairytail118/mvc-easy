/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.easy.core.captcha.CaptchaServlet;
import com.easy.core.security.exception.CodeAuthenticationServiceException;

/**
 * 用户名密码、验证码验证
 * 
 * @author wy
 * @version v 0.1 2013-9-26 下午10:26:54 wy Exp $
 */
public class EasyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /** 验证码接收的默认名称 */
    public static final String SPRING_SECURITY_FORM_CODE_KEY = "j_code";

    /** 日志 */
    protected final Logger     log                           = LoggerFactory.getLogger(this
                                                                 .getClass());

    /** 验证码的name */
    private String             codeParameter                 = SPRING_SECURITY_FORM_CODE_KEY;

    /**
     * 重写，主要实现对验证码的拦截
     * 
     * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#attemptAuthentication(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
                                                                             throws AuthenticationException {
        checkCode(request, response);

        return super.attemptAuthentication(request, response);
    }

    /**
     * 验证验证码
     * 
     * @param request
     * @param response
     */
    private void checkCode(HttpServletRequest request, HttpServletResponse response) {
        String code = obtainCode(request);
        HttpSession session = request.getSession(false);
        if (session == null) {
            log.warn("登录验证时，获取的session为空");
            return;
        }
        String sesCode = (String) session.getAttribute(CaptchaServlet.CAPTCHA_CODE);
        //用过了就移除
        session.removeAttribute(CaptchaServlet.CAPTCHA_CODE);
        if (StringUtils.isBlank(code) || !StringUtils.equalsIgnoreCase(sesCode, code)) {
            throw new CodeAuthenticationServiceException("验证码不正确!");
        }

    }

    /**
     * 获取验证码
     * 
     * @param request
     * @return
     */
    protected String obtainCode(HttpServletRequest request) {
        return request.getParameter(codeParameter);
    }

    /**
     * Setter method for property <tt>codeParameter</tt>.
     * 
     * @param codeParameter
     *            value to be assigned to property codeParameter
     */
    public void setCodeParameter(String codeParameter) {
        this.codeParameter = codeParameter;
    }

}
