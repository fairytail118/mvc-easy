/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-11 上午7:55:00 wy Exp $
 */
public class RegexUtil {

	/**
	 * 验证邮箱是否正确
	 * 
	 * @param email
	 * @return
	 */
	public static boolean validEmail(String email) {
		String regex = "^(([a-zA-Z0-9]*[-_\\.]?)*[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		if (StringUtils.isBlank(email)) {
			return false;
		}

		return valid(regex, email);
	}

	/**
	 * 验证格式
	 * 
	 * @param regex
	 * @param data
	 * @return
	 */
	public static boolean valid(String regex, String data) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(data);
		return matcher.matches();
	}

}
