/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.enums;

import com.easy.core.enums.IEnum;

/**
 * 登录状态
 * 
 * @author wy
 * @version v 0.1 2013-9-28 上午9:09:39 wy Exp $
 */
public enum LoginLogStatus implements IEnum {

    /** 成功 */
    SUCCEED("succeed", "成功"),

    /** 失败 */
    ABORTED("aborted", "失败");

    /** 编码 */
    private String code;

    /** 说明 */
    private String desc;

    LoginLogStatus(String code, String desc) {
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
