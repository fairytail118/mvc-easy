/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.mvc.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.easy.core.utils.RequestUtil;
import com.easy.core.validator.Validator;
import com.easy.core.validator.annotations.Validations;

/**
 * 验证Aop拦截
 * 
 * @author wy
 * @version v 0.1 2013-9-15 上午8:13:38 wy Exp $
 */
public class EasyValidatorInterceptor extends HandlerInterceptorAdapter
		implements InitializingBean {

	private Validator validator;

	/**
	 * Setter method for property <tt>validator</tt>.
	 * 
	 * @param validator
	 *            value to be assigned to property validator
	 */
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	/**
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		Method method = ((HandlerMethod) handler).getMethod();

		// 如果不是验证的方法直接放行
		if (method.getAnnotation(Validations.class) == null) {

			return true;
		}

		Validations validations = method.getAnnotation(Validations.class);

		Map<String, String> map = validator.valid(validations, request);
		RequestUtil.setFormErrors(request, map);

		return true;

	}

	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(validator, "验证器validator不能为空");
	}

}
