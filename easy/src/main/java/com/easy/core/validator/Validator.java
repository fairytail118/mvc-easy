/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.easy.core.validator.annotations.Validations;

/**
 * 验证
 * 
 * @author wy
 * @version v 0.1 2013-9-10 下午10:51:01 wy Exp $
 */
public interface Validator {

	/**
	 * 验证数据
	 * 
	 * @param validations
	 * @param request
	 * @return 返回 字段-对应message
	 */
	public Map<String, String> valid(Validations validations,
			HttpServletRequest request);

}
