/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.dao;

import java.util.List;

import com.easy.admin.entity.User;
import com.easy.core.common.Page;

/**
 * 用户Dao
 * 
 * @author wy
 * @version v 0.1 13-09-23 下午 20:32:31 wy Exp $
 */
public interface UserDao {

    /**
     * 保存
     * 
     * @param user
     * @return
     */
    public User create(User user);

    /**
     * 更新
     * 
     * @param user
     * @param 返回更新数量
     */
    public int update(User user);

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
    public User getByPrimaryKey(Long id );

    /**
     * 条件查询列表
     * 
     * @param user
     * @return
     */
    public List<User> selectByCriteria(User user);

    /**
     * 条件查询数量
     * 
     * @param user
     * @return
     */
    public int countByCriteria(User user);

    /**
     * 分页查询
     * 
     * @param page
     */
    public void page(Page<User> page);

}
