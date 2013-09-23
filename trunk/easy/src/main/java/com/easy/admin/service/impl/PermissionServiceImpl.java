/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.easy.admin.dao.PermissionDao;
import com.easy.admin.entity.Permission;
import com.easy.admin.service.PermissionService;
import com.easy.core.common.Page;

/**
 * 权限ServiceImpl
 * 
 * @author wy
 * @version v 0.1 2013-9-3 下午11:23:27 wy Exp $
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionDao permissionDao;

    /**
     * @see com.easy.admin.service.PermissionService#save(com.easy.admin.entity.Permission)
     */
    @Override
    public Permission save(Permission permission) {
        if (permission.getId() == null) {
            permissionDao.create(permission);
        } else {
            permissionDao.update(permission);
        }
        return permission;
    }

    /**
     * @see com.easy.admin.service.PermissionService#deleteByPrimaryKeys(java.lang.Long[])
     */
    @Override
    public void deleteByPrimaryKeys(java.lang.Long[] keys) {
        permissionDao.deleteByPrimaryKeys(keys);
    }

    /**
     * @see com.easy.admin.service.PermissionService#getByPrimaryKey(java.lang.Long)
     */
    @Override
    public Permission getByPrimaryKey(Long id) {

        return permissionDao.getByPrimaryKey(id);
    }

    /**
     * @see com.easy.admin.service.PermissionService#page(com.easy.core.common.Page)
     */
    @Override
    public void page(Page<Permission> page) {
        permissionDao.page(page);
    }

}
