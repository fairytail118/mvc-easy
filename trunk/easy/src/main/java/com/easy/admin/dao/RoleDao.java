/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.dao;

import java.util.List;

import com.easy.admin.entity.Role;
import com.easy.core.common.Page;

/**
 * 角色Dao
 * 
 * @author wy
 * @version v 0.1 13-09-23 下午 20:32:33 wy Exp $
 */
public interface RoleDao {

    /**
     * 保存
     * 
     * @param role
     * @return
     */
    public Role create(Role role);

    /**
     * 更新
     * 
     * @param role
     * @param 返回更新数量
     */
    public int update(Role role);

    /**
     * 根据主键删除
     * 
     * @param ids 
     * @return
     */
    public int deleteByPrimaryKeys(Long...ids );

    /**
     * 根据主键查询
     * 
     * @param  id 
     * @return
     */
    public Role getByPrimaryKey(Long id );

    /**
     * 条件查询列表
     * 
     * @param role
     * @return
     */
    public List<Role> selectByCriteria(Role role);

    /**
     * 条件查询数量
     * 
     * @param role
     * @return
     */
    public int countByCriteria(Role role);

    /**
     * 分页查询
     * 
     * @param page
     */
    public void page(Page<Role> page);

}
