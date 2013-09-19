/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.dao;

import java.util.List;

import com.easy.admin.entity.AllotRole;

/**
 * 分配管理员Dao
 * 
 * @author wy
 * @version v 0.1 2013-9-19 上午11:10:41 wy Exp $
 */
public interface AllotRoleDao {

	/**
	 * 保存
	 * 
	 * @param allotRole
	 * @return
	 */
	public AllotRole create(AllotRole allotRole);

	/**
	 * 更新
	 * 
	 * @param allotRole
	 * @param 返回更新数量
	 */
	public int update(AllotRole allotRole);

	/**
	 * 根据主键查询
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public AllotRole getByPrimaryKey(Long userId, Long roleId);

	/**
	 * 根据主键删除
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public int deleteByPrimaryKeys(Long userId, Long roleId);

	/**
	 * 条件查询列表
	 * 
	 * @param allotRole
	 * @return
	 */
	public List<AllotRole> selectByCriteria(AllotRole allotRole);

}
