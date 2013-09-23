/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.dao;

import java.util.List;

import com.easy.admin.entity.Permission;
import com.easy.core.common.Page;

/**
 * 权限Dao
 * 
 * @author wy
 * @version v 0.1 13-09-23 下午 20:32:33 wy Exp $
 */
public interface PermissionDao {

    /**
     * 保存
     * 
     * @param permission
     * @return
     */
    public Permission create(Permission permission);

    /**
     * 更新
     * 
     * @param permission
     * @param 返回更新数量
     */
    public int update(Permission permission);

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
    public Permission getByPrimaryKey(Long id );

    /**
     * 条件查询列表
     * 
     * @param permission
     * @return
     */
    public List<Permission> selectByCriteria(Permission permission);

    /**
     * 条件查询数量
     * 
     * @param permission
     * @return
     */
    public int countByCriteria(Permission permission);

    /**
     * 分页查询
     * 
     * @param page
     */
    public void page(Page<Permission> page);

}
