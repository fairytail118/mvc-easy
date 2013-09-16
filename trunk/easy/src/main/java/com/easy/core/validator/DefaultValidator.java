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

import com.easy.core.mvc.MessageResolver;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-15 上午9:12:32 wy Exp $
 */
public class DefaultValidator extends AbstractValidator implements
		InitializingBean {

	/**
	 * @see com.easy.core.validator.AbstractValidator#handleResult(java.util.List,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Map<String, String> handleResult(List<ValidatorResult> list,
			HttpServletRequest request) {

		Map<String, String> map = new LinkedHashMap<String, String>();

		for (ValidatorResult r : list) {
			// 如果包含字段
			if (map.containsKey(r.getField())) {
				continue;
			}
			String msg = null;
			if (StringUtils.isNotBlank(r.getKey())) {
				msg = MessageResolver.getMessage(request, r.getKey(), r
						.getValidParam().values().toArray());
			}
			if (StringUtils.isBlank(msg) && StringUtils.isBlank(r.getMessage())) {
				log.warn(
						"表单验证field:{}时根据key{}默认message{}获取的验证错误字符串为空",
						new String[] { r.getField(), r.getKey(), r.getMessage() });
				msg = "无对应的message";
			} else if (StringUtils.isBlank(msg)) {
				msg = r.getMessage();
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
		log.info("DefaultValidator is Init");
	}

}
