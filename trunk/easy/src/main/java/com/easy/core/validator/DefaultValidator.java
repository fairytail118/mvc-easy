/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.Assert;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-15 上午9:12:32 wy Exp $
 */
public class DefaultValidator extends AbstractValidator implements
		InitializingBean {

	/** 资源文件支持 */
	private ResourceBundleMessageSource messageSource;

	/**
	 * Setter method for property <tt>MessageSource</tt>.
	 * 
	 * @param MessageSource
	 *            value to be assigned to property MessageSource
	 */
	public void setMessageSource(ResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}

	/**
	 * @see com.easy.core.validator.AbstractValidator#handleResult(java.util.List,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, String> handleResult(List<ValidatorResult> list,
			HttpServletRequest request) {

		Map<String, String> map = new LinkedHashMap<String, String>();

		for (ValidatorResult r : list) {
			String msg = r.getMessage();
			if (StringUtils.isNotBlank(r.getKey())) {
				msg = messageSource.getMessage(r.getKey(), r.getValues(),
						r.getMessage(), request.getLocale());
			}
			if (StringUtils.isEmpty(msg)) {
				log.warn(
						"表单验证field:{}时根据key{}默认message{}获取的验证错误字符串为空",
						new String[] { r.getField(), r.getKey(), r.getMessage() });
				msg = "无对应的message";
			}
			map.put(r.getField(), msg);
		}
		return map;
	}

	/**
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(messageSource, "表单验证setMessageSource为空!");
	}

}
