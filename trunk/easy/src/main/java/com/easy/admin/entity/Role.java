/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.entity;

import com.easy.core.entity.IdEntity;

/**
 * 角色
 * 
 * @author wy
 * @version v 0.1 2013-10-04 上午 00:09:15 wy Exp $
 */
public class Role extends IdEntity {

    /** serialVersionUID */
    private static final long serialVersionUID = -765606215091270305L;

    /** 角色名称 */
    private String            name;

    /** 角色编码 */
    private String            code;

    /** 是否系统 */
    private Boolean           isSystem;

    /**
     * Getter method for property <tt>name</tt>.
     * 
     * @return property value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method for property <tt>name</tt>.
     * 
     * @param name value to be assigned to property name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method for property <tt>code</tt>.
     * 
     * @return property value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter method for property <tt>code</tt>.
     * 
     * @param code value to be assigned to property code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter method for property <tt>isSystem</tt>.
     * 
     * @return property value of isSystem
     */
    public Boolean getIsSystem() {
        return isSystem;
    }

    /**
     * Setter method for property <tt>isSystem</tt>.
     * 
     * @param isSystem value to be assigned to property isSystem
     */
    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }

}
