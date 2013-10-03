/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.service;

import com.easy.admin.entity.LoginLog;
import com.easy.core.common.Page;

/**
 * 登陆日志Service
 * 
 * @author wy
 * @version v 0.1 13-09-28 上午 00:49:47 wy Exp $
 */
public interface LoginLogService {

    /**
     * 保存管理员,根据ID判断是保存还是更新
     * 
     * @param loginLog
     * @return
     */
    public LoginLog save(LoginLog loginLog);

    /**
     * 根据主键删除
     * 
     * @param keys
     * @return
     */
    public void deleteByPrimaryKeys(Long[] keys);

    /**
     * 根据主键查询
     * 
     * @param id
     * @return
     */
    public LoginLog getByPrimaryKey(Long id);

    /**
     * 分页查询
     * 
     * @param page
     */
    public void page(Page<LoginLog> page);

}
