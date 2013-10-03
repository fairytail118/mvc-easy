/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.dao;

import java.util.List;

import com.easy.admin.entity.LoginLog;
import com.easy.core.common.Page;

/**
 * 登陆日志Dao
 * 
 * @author wy
 * @version v 0.1 13-09-28 上午 00:49:46 wy Exp $
 */
public interface LoginLogDao {

    /**
     * 保存
     * 
     * @param loginLog
     * @return
     */
    public LoginLog create(LoginLog loginLog);

    /**
     * 更新
     * 
     * @param loginLog
     * @param 返回更新数量
     */
    public int update(LoginLog loginLog);

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
    public LoginLog getByPrimaryKey(Long id);

    /**
     * 条件查询列表
     * 
     * @param loginLog
     * @return
     */
    public List<LoginLog> selectByCriteria(LoginLog loginLog);

    /**
     * 条件查询数量
     * 
     * @param loginLog
     * @return
     */
    public int countByCriteria(LoginLog loginLog);

    /**
     * 分页查询
     * 
     * @param page
     */
    public void page(Page<LoginLog> page);

}
