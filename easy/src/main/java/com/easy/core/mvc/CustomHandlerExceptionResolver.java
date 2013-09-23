/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.mvc;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.easy.core.mvc.result.Result;

/**
 * 
 * @author wy
 * @version v 0.1 2013-6-17 下午9:53:43 wy Exp $
 */
public class CustomHandlerExceptionResolver extends ExceptionHandlerExceptionResolver {

    /** 日志 */
    private final static Logger log = LoggerFactory.getLogger(CustomHandlerExceptionResolver.class);

    /**
     * @see org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver#doResolveHandlerMethodException(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse,
     *      org.springframework.web.method.HandlerMethod, java.lang.Exception)
     */
    @Override
    protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request,
                                                           HttpServletResponse response,
                                                           HandlerMethod handlerMethod, Exception ex) {

        log.error("controller中抛出异常:", ex);
        if (handlerMethod != null && handlerMethod.getMethod() != null) {
            Method method = handlerMethod.getMethod();
            log.error("{}#{}抛出异常:{}",
                new Object[] { method.getDeclaringClass().getName(), method.getName(), ex });
            ResponseBody responseBody = AnnotationUtils.findAnnotation(method, ResponseBody.class);
            if (responseBody != null) {
                ResponseStatus responseStatusAnn = AnnotationUtils.findAnnotation(method,
                    ResponseStatus.class);
                if (responseStatusAnn != null) {
                    return super.doResolveHandlerMethodException(request, response, handlerMethod,
                        ex);
                }
                try {
                    response.getWriter().write(
                        new ObjectMapper().writeValueAsString(new Result(ex)));
                }
                catch (IOException e) {
                    log.error("json结果转换异常", e);
                }
                return new ModelAndView();

            }
        }

        return super.doResolveHandlerMethodException(request, response, handlerMethod, ex);
    }

}
