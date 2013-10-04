/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.entity;

import com.easy.core.entity.IdEntity;

/**
 * 资源
 * 
 * @author wy
 * @version v 0.1 2013-10-04 上午 00:11:33 wy Exp $
 */
public class Resource extends IdEntity {

    /** serialVersionUID */
    private static final long serialVersionUID = 723757588362588906L;

    /** 名称 */
    private String            name;

    /** URL */
    private String            url;

    /** 是否系统 */
    private Boolean           isSystem;

    /** 组编码 */
    private String            groupCode;

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
     * Getter method for property <tt>url</tt>.
     * 
     * @return property value of url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Setter method for property <tt>url</tt>.
     * 
     * @param url value to be assigned to property url
     */
    public void setUrl(String url) {
        this.url = url;
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

    /**
     * Getter method for property <tt>groupCode</tt>.
     * 
     * @return property value of groupCode
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * Setter method for property <tt>groupCode</tt>.
     * 
     * @param groupCode value to be assigned to property groupCode
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

}
