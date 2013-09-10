/**
 * cxybj.com Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.common;

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

}
