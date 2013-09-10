/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字符串必填
 * 
 * @author wy
 * @version v 0.1 2013-9-10 下午10:42:39 wy Exp $
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredStringValidator {

	/**
	 * 是否去除左右空格
	 * 
	 * @return
	 */
	boolean trim() default true;

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
	 * 字段
	 * 
	 * @return
	 */
	String field() default "";

}
