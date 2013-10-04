/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.easy.admin.dao.RoleDao;
import com.easy.admin.entity.Role;
import com.easy.admin.service.RoleService;
import com.easy.core.common.Page;
import com.easy.core.exceptions.EasyException;

/**
 * 角色ServiceImpl
 * 
 * @author wy
 * @version v 0.1 2013-9-3 下午11:23:27 wy Exp $
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao           roleDao;


    /**
     * @see com.easy.admin.service.RoleService#save(com.easy.admin.entity.Role,
     *      java.util.List)
     */
    @Override
    public Role save(Role role, List<Long> grantList) {
        if (this.checkCodeExists(role.getCode(), role.getId())) {
            throw new EasyException("编码已经存在!");
        }
        if (role.getId() == null) {
            roleDao.create(role);
        } else {
            roleDao.update(role);
            //rolePermissionDao.deleteByPrimaryKeys(role.getId(), null);
        }
//        RolePermission rolePermission = new RolePermission();
//        for (Long permissionId : grantList) {
//            rolePermission.setPermissionId(permissionId);
//            rolePermission.setRoleId(role.getId());
//            rolePermissionDao.create(rolePermission);
//        }
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

        Role role = roleDao.getByPrimaryKey(id);

        if (role != null) {
//            RolePermission rolePermission = new RolePermission();
//            rolePermission.setRoleId(role.getId());
//
//            List<RolePermission> list = rolePermissionDao.selectByCriteria(rolePermission);
//
//            role.put("grantList", list);
        }

        return role;
    }

    /**
     * @see com.easy.admin.service.RoleService#page(com.easy.core.common.Page)
     */
    @Override
    public void page(Page<Role> page) {
        roleDao.page(page);
    }

    /**
     * @see com.easy.admin.service.RoleService#checkCodeExists(java.lang.String,
     *      java.lang.Long)
     */
    @Override
    public boolean checkCodeExists(String code, Long id) {
        Role role = new Role();
        role.setCode(code);
        List<Role> list = roleDao.selectByCriteria(role);
        if (list.isEmpty()) {
            return false;
        }
        //如果没有id，但是存在
        else if (id == null) {
            return true;
        } else if (id.equals(list.get(0).getId())) {
            return false;
        }
        return true;
    }

}
