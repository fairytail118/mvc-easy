/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.easy.admin.dao.RoleResourceDao;
import com.easy.admin.entity.RoleResource;
import java.util.HashMap;
import java.util.Map;
import com.easy.core.dao.impl.MyBatisGenericDao;

/**
 * 角色资源DaoImpl
 * 
 * @author wy
 * @version v 0.1 2013-10-04 上午 00:11:33 wy Exp $
 */
@Repository
public class RoleResourceDaoImpl extends MyBatisGenericDao implements RoleResourceDao {

    /**
     * @see com.easy.admin.dao.RoleResourceDao#create(com.easy.admin.entity.RoleResource)
     */
    @Override
    public RoleResource create(RoleResource roleResource) {
        save("com.easy.admin.dao.RoleResourceDao.create", roleResource);
        return roleResource;
    }

    /**
     * @see com.easy.admin.dao.RoleResourceDao#update(com.easy.admin.entity.RoleResource)
     */
    @Override
    public int update(RoleResource roleResource) {
        return update("com.easy.admin.dao.RoleResourceDao.update", roleResource);
    }

    /**
     * @see com.easy.admin.dao.RoleResourceDao#getByPrimaryKey(java.lang.Long
     *      ,java.lang.Long )
     */
    @Override
    public RoleResource getByPrimaryKey(Long roleId, Long resourceId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId", roleId);
        params.put("resourceId", resourceId);

        return get("com.easy.admin.dao.RoleResourceDao.getByPrimaryKey", params);
    }

    /**
     * @see com.easy.admin.dao.RoleResourceDao#deleteByPrimaryKeys(java.lang.Long
     *      ,java.lang.Long )
     */
    @Override
    public int deleteByPrimaryKeys(Long roleId, Long resourceId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId", roleId);
        params.put("resourceId", resourceId);

        return delete("com.easy.admin.dao.RoleResourceDao.deleteByPrimaryKeys", params);
    }

    /**
     * @see com.easy.admin.dao.RoleResourceDao#selectByCriteria(com.easy.admin.entity.RoleResource)
     */
    @Override
    public List<RoleResource> selectByCriteria(RoleResource roleResource) {
        return list("com.easy.admin.dao.RoleResourceDao.selectByCriteria", roleResource);
    }
}
