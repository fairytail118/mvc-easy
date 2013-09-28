/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.easy.admin.dao.LoginLogDao;
import com.easy.admin.entity.LoginLog;
import com.easy.admin.service.LoginLogService;
import com.easy.core.common.Page;

/**
 * 登陆日志ServiceImpl
 * 
 * @author wy
 * @version v 0.1 2013-9-3 下午11:23:27 wy Exp $
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Resource
    private LoginLogDao loginLogDao;

    /**
     * @see com.easy.admin.service.LoginLogService#save(com.easy.admin.entity.LoginLog)
     */
    @Override
    public LoginLog save(LoginLog loginLog) {
        if (loginLog.getId() == null) {
            loginLogDao.create(loginLog);
        } else {
            loginLogDao.update(loginLog);
        }
        return loginLog;
    }

    /**
     * @see com.easy.admin.service.LoginLogService#deleteByPrimaryKeys(java.lang.Long[])
     */
    @Override
    public void deleteByPrimaryKeys(java.lang.Long[] keys) {
        loginLogDao.deleteByPrimaryKeys(keys);
    }

    /**
     * @see com.easy.admin.service.LoginLogService#getByPrimaryKey(java.lang.Long)
     */
    @Override
    public LoginLog getByPrimaryKey(Long id) {

        return loginLogDao.getByPrimaryKey(id);
    }

    /**
     * @see com.easy.admin.service.LoginLogService#page(com.easy.core.common.Page)
     */
    @Override
    public void page(Page<LoginLog> page) {
        loginLogDao.page(page);
    }

}
