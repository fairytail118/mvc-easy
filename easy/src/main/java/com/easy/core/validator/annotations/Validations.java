/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 验证
 * 
 * @author wy
 * @version v 0.1 2013-9-10 下午10:45:33 wy Exp $
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Validations {

	/**
	 * 邮箱验证
	 */
	public EmailValidator[] emailValidators() default {};

	/**
	 * 数字范围验证
	 */
	public NumberRangeValidator[] numberRangeValidators() default {};

	/**
	 * 数字验证
	 */
	public NumberValidator[] numberValidators() default {};

	/**
	 * 正则验证
	 */
	public RegexValidator[] regexValidators() default {};

	/**
	 * 必填字符串验证
	 */
	public RequiredStringValidator[] requiredStringValidators() default {};

	/**
	 * 必填验证
	 */
	public RequiredValidator[] requiredValidators() default {};

	/**
	 * 字符串长度验证
	 */
	public StringLengthValidator[] stringLengthValidators() default {};

	/**
	 * 验证错误中断
	 * 
	 * @return
	 */
	public boolean interruptForError() default true;

}
