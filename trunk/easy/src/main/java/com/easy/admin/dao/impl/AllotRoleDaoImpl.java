/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.easy.admin.dao.AllotRoleDao;
import com.easy.admin.entity.AllotRole;
import com.easy.core.dao.impl.MyBatisGenericDao;

/**
 * 分配管理员DaoImpl
 * 
 * @author wy
 * @version v 0.1 2013-9-19 上午11:25:31 wy Exp $
 */
public class AllotRoleDaoImpl extends MyBatisGenericDao implements AllotRoleDao {

    /**
     * @see com.easy.admin.dao.AllotRoleDao#create(com.easy.admin.entity.AllotRole)
     */
    @Override
    public AllotRole create(AllotRole allotRole) {
        save("com.easy.dao.AllotRoleDao.create", allotRole);
        return allotRole;
    }

    /**
     * @see com.easy.admin.dao.AllotRoleDao#update(com.easy.admin.entity.AllotRole)
     */
    @Override
    public int update(AllotRole allotRole) {
        return update("com.easy.dao.AllotRoleDao.update", allotRole);
    }

    /**
     * @see com.easy.admin.dao.AllotRoleDao#getByPrimaryKey(java.lang.Long,
     *      java.lang.Long)
     */
    @Override
    public AllotRole getByPrimaryKey(Long userId, Long roleId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("roleId", roleId);
        return get("com.easy.dao.AllotRoleDao.getByPrimaryKey", params);
    }

    /**
     * @see com.easy.admin.dao.AllotRoleDao#deleteByPrimaryKeys(java.lang.Long,
     *      java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKeys(Long userId, Long roleId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("roleId", roleId);
        return get("com.easy.dao.AllotRoleDao.deleteByPrimaryKeys", params);
    }

    /**
     * @see com.easy.admin.dao.AllotRoleDao#selectByCriteria(com.easy.admin.entity.AllotRole)
     */
    @Override
    public List<AllotRole> selectByCriteria(AllotRole allotRole) {
        return list("com.easy.dao.AllotRoleDao.selectByCriteria", allotRole);
    }

}
