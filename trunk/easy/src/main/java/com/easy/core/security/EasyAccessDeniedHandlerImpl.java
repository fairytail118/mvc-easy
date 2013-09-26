/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import com.easy.core.mvc.result.Result;

/**
 * 自定义的无权限处理，主要处理ajax
 * 
 * @author wy
 * @version v 0.1 2013-9-26 下午6:55:15 wy Exp $
 */
public class EasyAccessDeniedHandlerImpl extends AccessDeniedHandlerImpl {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException,
                                                                   ServletException {
        if (!response.isCommitted()) {

            String requestWith = request.getHeader("X-Requested-With");
            // 如果是ajax请求
            if (StringUtils.endsWithIgnoreCase("XMLHttpRequest", requestWith)) {

                Result result = new Result("无权限操作");
                result.setDenied(true);
                response.getWriter().write(new ObjectMapper().writeValueAsString(result));

            } else {
                super.handle(request, response, accessDeniedException);
            }
        }

    }
}
