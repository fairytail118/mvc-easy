/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数字验证
 * 
 * @author wy
 * @version v 0.1 2013-9-10 下午10:33:43 wy Exp $
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberRangeValidator {

	/**
	 * 文字消息
	 * 
	 * @return
	 */
	String message() default "";

	/**
	 * 资源文件key
	 */
	String key() default "";

	/**
	 * 最小
	 */
	String min() default "";

	/**
	 * 是否小数
	 * 
	 * @return
	 */
	boolean decimals() default false;

	/**
	 * 最大
	 */
	String max() default "";

	/**
	 * 字段
	 * 
	 * @return
	 */
	String field() default "";
}
