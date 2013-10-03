/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.easy.admin.dao.PermissionDao;
import com.easy.admin.entity.Permission;
import com.easy.admin.service.PermissionService;
import com.easy.core.common.Page;
import com.easy.core.exceptions.EasyException;

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
        if (this.checkCodeExists(permission.getCode(), permission.getId())) {
            throw new EasyException("编码已经存在!");
        }
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
        //删除role_permission中关联的记录
        //        for(long permissionId : keys){
        //        	rolePermissionDao.deleteByPrimaryKeys(null, permissionId);
        //        }
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

        for (Permission permission : page.getList()) {
            if (permission.getParentId() != null) {
                permission.setParentPermission(permissionDao.getByPrimaryKey(permission
                    .getParentId()));
            }
        }

    }

    /**
     * @see com.easy.admin.service.PermissionService#firstPermissionList()
     */
    @Override
    public List<Permission> firstPermissionList() {
        return permissionDao.firstPermissionList();
    }

    /**
     * @see com.easy.admin.service.PermissionService#checkCodeExists(java.lang.String,
     *      java.lang.Long)
     */
    @Override
    public boolean checkCodeExists(String code, Long id) {
        Permission permission = new Permission();
        permission.setCode(code);
        List<Permission> list = permissionDao.selectByCriteria(permission);
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

    /**
     * @see com.easy.admin.service.PermissionService#gradeList()
     */
    @Override
    public List<Permission> gradeList() {

        List<Permission> list = this.firstPermissionList();

        Permission query = new Permission();

        for (Permission permission : list) {
            query.setParentId(permission.getId());
            List<Permission> sonList = permissionDao.selectByCriteria(query);
            permission.setList(sonList);
        }
        return list;
    }

}
