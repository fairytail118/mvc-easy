/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-11 上午8:07:12 wy Exp $
 */
public abstract class AbstractFieldValidator<T> implements FieldValidator<T> {

	/** 日志 */
	protected final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * 获取参数值
	 * 
	 * @param name
	 * @param request
	 * @return
	 */
	public String[] getParameters(String name, HttpServletRequest request) {

		if (name != null && name.trim().length() == 0) {
			log.warn("验证时，去request中获取的name参数为空");
			return new String[0];
		}

		String[] values = request.getParameterValues(name);
		if (values == null) {
			return new String[0];
		}

		return values;
	}

}
