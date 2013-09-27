/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.enums;

import com.easy.core.enums.IEnum;

/**
 * 用户类型
 * 
 * @author wy
 * @version v 0.1 2013-9-27 下午8:20:26 wy Exp $
 */
public enum UserType implements IEnum {

    /** 超级管理员 */
    super_admin("super_admin", "超级管理员"),

    /** 管理员 */
    admin("admin", "普通管理员"),

    /** 一般用户 */
    user("user", "一般用户");

    /** 编码 */
    private String code;

    /** 说明 */
    private String desc;

    UserType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * @see com.easy.core.enums.IEnum#getDesc()
     */
    @Override
    public String getDesc() {
        return desc;
    }

    /**
     * @see com.easy.core.enums.IEnum#getCode()
     */
    @Override
    public String getCode() {
        return code;
    }

}
