/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.easy.admin.dao.UserRoleDao;
import com.easy.admin.entity.UserRole;
import com.easy.core.dao.impl.MyBatisGenericDao;

/**
 * 分配角色DaoImpl
 * 
 * @author wy
 * @version v 0.1 13-09-23 下午 20:32:32 wy Exp $
 */
@Repository
public class UserRoleDaoImpl extends MyBatisGenericDao implements UserRoleDao {

    /**
     * @see com.easy.admin.dao.UserRoleDao#create(com.easy.admin.entity.UserRole)
     */
    @Override
    public UserRole create(UserRole userRole) {
        save("com.easy.admin.dao.UserRoleDao.create", userRole);
        return userRole;
    }

    /**
     * @see com.easy.admin.dao.UserRoleDao#update(com.easy.admin.entity.UserRole)
     */
    @Override
    public int update(UserRole userRole) {
        return update("com.easy.admin.dao.UserRoleDao.update", userRole);
    }

    /**
     * @see com.easy.admin.dao.UserRoleDao#getByPrimaryKey(java.lang.Long
     *      ,java.lang.Long )
     */
    @Override
    public UserRole getByPrimaryKey(Long userId, Long roleId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("roleId", roleId);

        return get("com.easy.admin.dao.UserRoleDao.getByPrimaryKey", params);
    }

    /**
     * @see com.easy.admin.dao.UserRoleDao#deleteByPrimaryKeys(java.lang.Long
     *      ,java.lang.Long )
     */
    @Override
    public int deleteByPrimaryKeys(Long userId, Long roleId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("roleId", roleId);

        return delete("com.easy.admin.dao.UserRoleDao.deleteByPrimaryKeys", params);
    }

    /**
     * @see com.easy.admin.dao.UserRoleDao#selectByCriteria(com.easy.admin.entity.UserRole)
     */
    @Override
    public List<UserRole> selectByCriteria(UserRole userRole) {
        return list("com.easy.admin.dao.UserRoleDao.selectByCriteria", userRole);
    }
}
