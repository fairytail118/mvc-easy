/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.easy.admin.dao.RoleDao;
import com.easy.admin.entity.Role;
import com.easy.admin.service.RoleService;
import com.easy.core.common.Page;

/**
 * 角色ServiceImpl
 * 
 * @author wy
 * @version v 0.1 2013-9-3 下午11:23:27 wy Exp $
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    /**
     * @see com.easy.admin.service.RoleService#save(com.easy.admin.entity.Role)
     */
    @Override
    public Role save(Role role) {
        if (role.getId() == null) {
            roleDao.create(role);
        } else {
            roleDao.update(role);
        }
        return role;
    }

    /**
     * @see com.easy.admin.service.RoleService#deleteByPrimaryKeys(java.lang.Long[])
     */
    @Override
    public void deleteByPrimaryKeys(java.lang.Long[] keys) {
        roleDao.deleteByPrimaryKeys(keys);
    }

    /**
     * @see com.easy.admin.service.RoleService#getByPrimaryKey(java.lang.Long)
     */
    @Override
    public Role getByPrimaryKey(Long id) {

        return roleDao.getByPrimaryKey(id);
    }

    /**
     * @see com.easy.admin.service.RoleService#page(com.easy.core.common.Page)
     */
    @Override
    public void page(Page<Role> page) {
        roleDao.page(page);
    }

}
