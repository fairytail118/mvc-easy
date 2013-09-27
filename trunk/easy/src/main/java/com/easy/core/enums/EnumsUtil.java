/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.enums;

/**
 * 枚举工具类
 * 
 * @author wy
 * @version v 0.1 2013-9-27 下午8:57:38 wy Exp $
 */
public class EnumsUtil {

    /**
     * 获取枚举
     * 
     * @param clazz
     * @param code
     * @return
     */
    public static <T extends IEnum> T valueOfIgnoreCase(Class<T> clazz, String code) {

        return valueOf(clazz, code, true);
    }

    /**
     * 获取枚举,区分大小写
     * 
     * @param clazz
     * @param code
     * @return
     */
    public static <T extends IEnum> T valueOf(Class<T> clazz, String code) {

        return valueOf(clazz, code, false);
    }

    /**
     * 获取枚举,区分大小写
     * 
     * @param clazz
     * @param code
     * @param ignoreCase
     * @return
     */
    private static <T extends IEnum> T valueOf(Class<T> clazz, String code, boolean ignoreCase) {

        //得到values
        T[] enums = values(clazz);

        if (enums == null || enums.length == 0) {
            return null;
        }

        for (T t : enums) {
            if (ignoreCase && t.getCode().equalsIgnoreCase(code)) {
                return t;
            } else if (t.getCode().equals(code)) {
                return t;
            }
        }
        return null;
    }

    /**
     * 获取枚举集合
     * 
     * @param clazz
     * @return
     */
    public static <T extends IEnum> T[] values(Class<T> clazz) {
        if (!clazz.isEnum()) {
            throw new IllegalArgumentException("Class[" + clazz + "]不是枚举类型");
        }
        //得到values
        return clazz.getEnumConstants();
    }

}
