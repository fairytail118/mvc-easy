/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.mvc.freemarker.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easy.core.enums.EnumsUtil;
import com.easy.core.enums.IEnum;

/**
 * 枚举工具类
 * 
 * @author wy
 * @version v 0.1 2013-9-27 下午10:37:57 wy Exp $
 */
public class EnumsTool {

    /** 日志 */
    private static final Logger log = LoggerFactory.getLogger(EnumsTool.class);

    /**
     * 获取
     * 
     * @param enumClass
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<IEnum> values(String enumClass) {
        try {
            Class<?> clazz = Class.forName(enumClass);
            if (!IEnum.class.isAssignableFrom(clazz)) {
                log.error("{} is not AssignableFrom IEnum", enumClass);
                return new ArrayList<IEnum>();
            }
            IEnum[] enums = EnumsUtil.values((Class<IEnum>) clazz);
            return Arrays.asList(enums);

        }
        catch (ClassNotFoundException e) {
            log.error("{} is not Exists", enumClass);
        }

        return new ArrayList<IEnum>();
    }

    /**
     * 转换
     * 
     * @param enumClass
     * @param code
     * @return
     */
    @SuppressWarnings("unchecked")
    public IEnum valueOf(String enumClass, String code) {

        IEnum iEnum = null;

        try {
            Class<?> clazz = Class.forName(enumClass);
            if (!IEnum.class.isAssignableFrom(clazz)) {
                log.error("{} is not AssignableFrom IEnum", enumClass);
            } else {
                iEnum = EnumsUtil.valueOfIgnoreCase((Class<IEnum>) clazz, code);
            }

        }
        catch (ClassNotFoundException e) {
            log.error("{} is not Exists", enumClass);
        }

        //初始化一个空的，避免页面报错
        if (iEnum == null) {
            iEnum = new IEnum() {

                @Override
                public String getDesc() {
                    return "";
                }

                @Override
                public String getCode() {
                    return "";
                }
            };
        }

        return iEnum;

    }

}
