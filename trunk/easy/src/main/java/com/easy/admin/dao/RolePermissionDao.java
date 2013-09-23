/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.dao;

import java.util.List;

import com.easy.admin.entity.RolePermission;

/**
 * 分配权限Dao
 * 
 * @author wy
 * @version v 0.1 13-09-23 下午 20:32:34 wy Exp $
 */
public interface RolePermissionDao {

    /**
     * 保存
     * 
     * @param rolePermission
     * @return
     */
    public RolePermission create(RolePermission rolePermission);

    /**
     * 更新
     * 
     * @param rolePermission
     * @param 返回更新数量
     */
    public int update(RolePermission rolePermission);

	/**
	 * 根据主键查询
	 * 
	 * @param roleId
	 * @param permissionId
	 * @return
	 */
	public RolePermission getByPrimaryKey(Long roleId ,Long permissionId );

	/**
	 * 根据主键删除
	 * 
	 * @param roleId
	 * @param permissionId
	 * @return
	 */
	public int deleteByPrimaryKeys(Long roleId ,Long permissionId );

	/**
	 * 条件查询列表
	 * 
	 * @param rolePermission
	 * @return
	 */
	public List<RolePermission> selectByCriteria(RolePermission rolePermission);

}
