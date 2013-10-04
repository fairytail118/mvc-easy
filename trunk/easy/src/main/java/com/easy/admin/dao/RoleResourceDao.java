/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.dao;

import java.util.List;

import com.easy.admin.entity.RoleResource;

/**
 * 角色资源Dao
 * 
 * @author wy
 * @version v 0.1 2013-10-04 上午 00:11:33 wy Exp $
 */
public interface RoleResourceDao {

    /**
     * 保存
     * 
     * @param roleResource
     * @return
     */
    public RoleResource create(RoleResource roleResource);

    /**
     * 更新
     * 
     * @param roleResource
     * @param 返回更新数量
     */
    public int update(RoleResource roleResource);

	/**
	 * 根据主键查询
	 * 
	 * @param roleId
	 * @param resourceId
	 * @return
	 */
	public RoleResource getByPrimaryKey(Long roleId ,Long resourceId );

	/**
	 * 根据主键删除
	 * 
	 * @param roleId
	 * @param resourceId
	 * @return
	 */
	public int deleteByPrimaryKeys(Long roleId ,Long resourceId );

	/**
	 * 条件查询列表
	 * 
	 * @param roleResource
	 * @return
	 */
	public List<RoleResource> selectByCriteria(RoleResource roleResource);

}
