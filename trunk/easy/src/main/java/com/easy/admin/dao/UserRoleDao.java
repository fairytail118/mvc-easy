/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.dao;

import java.util.List;

import com.easy.admin.entity.UserRole;

/**
 * 分配角色Dao
 * 
 * @author wy
 * @version v 0.1 13-09-23 下午 20:32:32 wy Exp $
 */
public interface UserRoleDao {

    /**
     * 保存
     * 
     * @param userRole
     * @return
     */
    public UserRole create(UserRole userRole);

    /**
     * 更新
     * 
     * @param userRole
     * @param 返回更新数量
     */
    public int update(UserRole userRole);

    /**
     * 根据主键查询
     * 
     * @param userId
     * @param roleId
     * @return
     */
    public UserRole getByPrimaryKey(Long userId, Long roleId);

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
     * @param userRole
     * @return
     */
    public List<UserRole> selectByCriteria(UserRole userRole);

}
