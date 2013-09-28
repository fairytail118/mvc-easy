/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.enums;

import com.easy.core.enums.IEnum;

/**
 * 登录动作
 * 
 * @author wy
 * @version v 0.1 2013-9-28 上午8:47:59 wy Exp $
 */
public enum LoginLogAction implements IEnum {

    /** 登录 */
    LOGIN("login", "登录"),

    /** 退出 */
    LOGOUT("logout", "退出");
    /** 编码 */
    private String code;

    /** 说明 */
    private String desc;

    LoginLogAction(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * @see com.easy.core.enums.IEnum#getCode()
     */
    @Override
    public String getCode() {
        return code;
    }

    /**
     * @see com.easy.core.enums.IEnum#getDesc()
     */
    @Override
    public String getDesc() {
        return desc;
    }

}
