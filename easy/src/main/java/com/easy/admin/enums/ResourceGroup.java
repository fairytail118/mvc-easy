/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.enums;

import com.easy.core.enums.IEnum;

/**
 * 资源分组
 * 
 * @author wy
 * @version v 0.1 2013-10-4 上午8:00:15 wy Exp $
 */
public enum ResourceGroup implements IEnum {

    /** 资源管理 */
    RESOURCE("resource", "资源管理"),

    /** 角色管理 */
    ROLE("role", "角色管理"),

    /** 管理员管理 */
    ADMIN("admin", "管理员管理"),

    /** 系统设置 */
    SYSTEM("system", "系统设置");

    /** 编码 */
    private String code;

    /** 说明 */
    private String desc;

    ResourceGroup(String code, String desc) {
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
