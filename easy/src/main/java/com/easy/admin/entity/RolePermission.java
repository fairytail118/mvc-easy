/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.entity;

import com.easy.core.entity.BaseEntity;

/**
 * 分配权限
 * 
 * @author wy
 * @version v 0.1 13-09-23 下午 20:32:34 wy Exp $
 */
public class RolePermission extends BaseEntity {

    /** serialVersionUID */
    private static final long serialVersionUID = 580144299951056258L;

    /** 角色ID */
    private Long              roleId;

    /** 权限ID */
    private Long              permissionId;

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
     * Getter method for property <tt>permissionId</tt>.
     * 
     * @return property value of permissionId
     */
    public Long getPermissionId() {
        return permissionId;
    }

    /**
     * Setter method for property <tt>permissionId</tt>.
     * 
     * @param permissionId value to be assigned to property permissionId
     */
    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

}
