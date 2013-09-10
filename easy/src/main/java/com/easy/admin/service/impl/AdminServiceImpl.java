/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.easy.admin.dao.AdminDao;
import com.easy.admin.entity.Admin;
import com.easy.admin.service.AdminService;
import com.easy.common.Page;

/**
 * 管理员 ServiceImpl
 * 
 * @author wy
 * @version v 0.1 2013-9-3 下午11:23:27 wy Exp $
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;

    /**
     * @see com.easy.admin.service.AdminService#save(com.easy.admin.entity.Admin)
     */
    @Override
    public Admin save(Admin admin) {
        if (admin.getId() == null) {
            adminDao.create(admin);
        } else {
            adminDao.update(admin);
        }
        return admin;
    }

    /**
     * @see com.easy.admin.service.AdminService#deleteByPrimaryKeys(java.lang.Long[])
     */
    @Override
    public void deleteByPrimaryKeys(Long[] keys) {
        adminDao.deleteByPrimaryKeys(keys);
    }

    /**
     * @see com.easy.admin.service.AdminService#getByPrimaryKey(java.lang.Long)
     */
    @Override
    public Admin getByPrimaryKey(Long id) {

        return adminDao.getByPrimaryKey(id);
    }

    /**
     * @see com.easy.admin.service.AdminService#page(com.easy.common.Page)
     */
    @Override
    public void page(Page<Admin> page) {
        adminDao.page(page);
    }

}
