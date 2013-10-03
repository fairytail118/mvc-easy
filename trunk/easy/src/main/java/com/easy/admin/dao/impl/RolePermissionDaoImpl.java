/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.easy.admin.dao.RolePermissionDao;
import com.easy.admin.entity.RolePermission;
import java.util.HashMap;
import java.util.Map;
import com.easy.core.dao.impl.MyBatisGenericDao;

/**
 * 分配权限DaoImpl
 * 
 * @author wy
 * @version v 0.1 13-09-23 下午 20:32:34 wy Exp $
 */
@Repository
public class RolePermissionDaoImpl extends MyBatisGenericDao implements RolePermissionDao {

    /**
     * @see com.easy.admin.dao.RolePermissionDao#create(com.easy.admin.entity.RolePermission)
     */
    @Override
    public RolePermission create(RolePermission rolePermission) {
        save("com.easy.admin.dao.RolePermissionDao.create", rolePermission);
        return rolePermission;
    }

    /**
     * @see com.easy.admin.dao.RolePermissionDao#update(com.easy.admin.entity.RolePermission)
     */
    @Override
    public int update(RolePermission rolePermission) {
        return update("com.easy.admin.dao.RolePermissionDao.update", rolePermission);
    }

    /**
     * @see com.easy.admin.dao.RolePermissionDao#getByPrimaryKey(java.lang.Long
     *      ,java.lang.Long )
     */
    @Override
    public RolePermission getByPrimaryKey(Long roleId, Long permissionId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId", roleId);
        params.put("permissionId", permissionId);

        return get("com.easy.dao.RolePermissionDao.getByPrimaryKey", params);
    }

    /**
     * @see com.easy.admin.dao.RolePermissionDao#deleteByPrimaryKeys(java.lang.Long
     *      ,java.lang.Long )
     */
    @Override
    public int deleteByPrimaryKeys(Long roleId, Long permissionId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId", roleId);
        params.put("permissionId", permissionId);

        return get("com.easy.dao.RolePermissionDao.deleteByPrimaryKeys", params);
    }

    /**
     * @see com.easy.admin.dao.RolePermissionDao#selectByCriteria(com.easy.admin.entity.RolePermission)
     */
    @Override
    public List<RolePermission> selectByCriteria(RolePermission rolePermission) {
        return list("com.easy.dao.RolePermissionDao.selectByCriteria", rolePermission);
    }
}
