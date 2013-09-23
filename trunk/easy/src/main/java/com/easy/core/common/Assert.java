/**
 * cxybj.com Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.common;

import org.apache.commons.lang.BooleanUtils;

import com.easy.core.exceptions.ParamException;

/**
 * 断言参数
 * 
 * @author wy
 * @version v 0.1 2013-6-16 下午10:19:24 wy Exp $
 */
public class Assert {

    /**
     * 不为空
     * 
     * @param obj
     * @param message
     */
    public static void notNull(Object obj, String message) {
        if (obj == null) {
            throw new ParamException(message);
        }
    }

    /**
     * 数组不为空
     * 
     * @param objs
     * @param message
     */
    public static void notEmpty(Object[] objs, String message) {
        notNull(objs, message);
        if (objs.length == 0) {
            throw new ParamException(message);
        }
    }

    /**
     * 为true
     * 
     * @param arg
     * @param message
     */
    public static void isTrue(Boolean bool, String message) {
        if (!BooleanUtils.isTrue(bool)) {
            throw new ParamException(message);
        }
    }

    /**
     * 为false
     * 
     * @param arg
     * @param message
     */
    public static void isFalse(Boolean bool, String message) {
        if (!BooleanUtils.isFalse(bool)) {
            throw new ParamException(message);
        }
    }

    /**
     * 不为true
     * 
     * @param arg
     * @param message
     */
    public static void isNotTrue(Boolean bool, String message) {
        if (!BooleanUtils.isNotTrue(bool)) {
            throw new ParamException(message);
        }
    }

    /**
     * 不为false
     * 
     * @param arg
     * @param message
     */
    public static void isNotFalse(Boolean bool, String message) {
        if (!BooleanUtils.isNotFalse(bool)) {
            throw new ParamException(message);
        }
    }

}
