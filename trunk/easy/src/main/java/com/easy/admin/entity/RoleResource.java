/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.entity;

import com.easy.core.entity.BaseEntity;

/**
 * 角色资源
 * 
 * @author wy
 * @version v 0.1 2013-10-04 上午 00:11:32 wy Exp $
 */
public class RoleResource extends BaseEntity {

    /** serialVersionUID */
    private static final long serialVersionUID = 874307546324722039L;

    /** 角色ID */
    private Long              roleId;

    /** 资源ID */
    private Long              resourceId;

    /**
     * Getter method for property <tt>roleId</tt>.
     * 
     * @return property value of roleId
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * Setter method for property <tt>roleId</tt>.
     * 
     * @param roleId value to be assigned to property roleId
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * Getter method for property <tt>resourceId</tt>.
     * 
     * @return property value of resourceId
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * Setter method for property <tt>resourceId</tt>.
     * 
     * @param resourceId value to be assigned to property resourceId
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

}
