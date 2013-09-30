/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.entity;

import com.easy.core.entity.IdEntity;

/**
 * 权限
 * 
 * @author wy
 * @version v 0.1 13-09-23 下午 20:32:33 wy Exp $
 */
public class Permission extends IdEntity {

    /** serialVersionUID */
    private static final long serialVersionUID = -373712074504825616L;

    /** 父权限ID */
    private Long              parentId;

    /** 权限名称 */
    private String            name;

    /** 权限编码 */
    private String            code;

    /** 父权限 */
    private Permission        parentPermission;

    /**
     * Getter method for property <tt>parentId</tt>.
     * 
     * @return property value of parentId
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * Setter method for property <tt>parentId</tt>.
     * 
     * @param parentId value to be assigned to property parentId
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

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
     * Getter method for property <tt>parentPermission</tt>.
     * 
     * @return property value of parentPermission
     */
    public Permission getParentPermission() {
        return parentPermission;
    }

    /**
     * Setter method for property <tt>parentPermission</tt>.
     * 
     * @param parentPermission value to be assigned to property parentPermission
     */
    public void setParentPermission(Permission parentPermission) {
        this.parentPermission = parentPermission;
    }

}
