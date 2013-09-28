/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字符串相同比较
 * 
 * @author wy
 * @version v 0.1 2013-9-28 下午5:10:07 wy Exp $
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EqualsStringValidator {
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
    String field();

    /**
     * 比较的字段
     * 
     * @return
     */
    String otherField();

    /**
     * 是否忽略大小写，默认不忽略(false)
     * 
     * @return
     */
    boolean ignoreCase() default false;
}
