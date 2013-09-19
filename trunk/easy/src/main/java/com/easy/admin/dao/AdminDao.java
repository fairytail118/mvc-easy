/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.dao;

import java.util.List;

import com.easy.admin.entity.Admin;
import com.easy.core.common.Page;

/**
 * 管理员Dao
 * 
 * @author wy
 * @version v 0.1 2013-9-2 下午10:56:54 wy Exp $
 */
public interface AdminDao {

    /**
     * 保存
     * 
     * @param admin
     * @return
     */
    public Admin create(Admin admin);

    /**
     * 更新
     * 
     * @param admin
     * @param 返回更新数量
     */
    public int update(Admin admin);

    /**
     * 根据主键删除
     * 
     * @param ids
     * @return
     */
    public int deleteByPrimaryKeys(Long... ids);

    /**
     * 根据主键查询
     * 
     * @param id
     * @return
     */
    public Admin getByPrimaryKey(Long id);

    /**
     * 条件查询列表
     * 
     * @param admin
     * @return
     */
    public List<Admin> selectByCriteria(Admin admin);

    /**
     * 条件查询数量
     * 
     * @param admin
     * @return
     */
    public int countByCriteria(Admin admin);

    /**
     * 分页查询
     * 
     * @param page
     */
    public void page(Page<Admin> page);

}
