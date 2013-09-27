/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.easy.admin.dao.PermissionDao;
import com.easy.admin.entity.Permission;
import org.apache.ibatis.session.RowBounds;
import com.easy.core.common.Page;
import com.easy.core.dao.impl.MyBatisGenericDao;

/**
 * 权限DaoImpl
 * 
 * @author wy
 * @version v 0.1 13-09-23 下午 20:32:33 wy Exp $
 */
@Repository
public class PermissionDaoImpl extends MyBatisGenericDao implements PermissionDao {

    /**
     * @see com.easy.admin.dao.PermissionDao#create(com.easy.admin.entity.Permission)
     */
    @Override
    public Permission create(Permission permission) {
        save("com.easy.admin.dao.PermissionDao.create", permission);
        return permission;
    }

    /**
     * @see com.easy.admin.dao.PermissionDao#update(com.easy.admin.entity.Permission)
     */
    @Override
    public int update(Permission permission) {
        return update("com.easy.admin.dao.PermissionDao.update", permission);
    }

    /**
     * @see com.easy.admin.dao.PermissionDao#deleteByPrimaryKeys(java.lang.Long[])
     */
    @Override
    public int deleteByPrimaryKeys(Long... ids) {
        return delete("com.easy.admin.dao.PermissionDao.deleteByPrimaryKeys", ids);
    }

    /**
     * @see com.easy.admin.dao.PermissionDao#getByPrimaryKey(java.lang.Long)
     */
    @Override
    public Permission getByPrimaryKey(Long id) {
        return get("com.easy.admin.dao.PermissionDao.getByPrimaryKey", id);
    }

    /**
     * @see com.easy.admin.dao.PermissionDao#selectByCriteria(com.easy.admin.entity.Permission)
     */
    @Override
    public List<Permission> selectByCriteria(Permission permission) {
        return list("com.easy.admin.dao.PermissionDao.selectByCriteria", permission);
    }

    /**
     * @see com.easy.admin.dao.PermissionDao#countByCriteria(com.easy.admin.entity.Permission)
     */
    @Override
    public int countByCriteria(Permission permission) {
        return get("com.easy.admin.dao.PermissionDao.countByCriteria", permission);
    }

    /**
     * @see com.easy.admin.dao.PermissionDao#page(com.easy.core.common.Page)
     */
    @Override
    public void page(Page<Permission> page) {
        RowBounds rowBounds = new RowBounds(page.getOffset(), page.getPageSize());
        List<Permission> list = list("com.easy.admin.dao.PermissionDao.selectByCriteria",
            page.getCriteria(), rowBounds);
        page.setList(list);
        page.setTotalCount(countByCriteria(page.getCriteria()));
    }

    /**
     * @see com.easy.admin.dao.PermissionDao#selectByUserId(java.lang.Long)
     */
    @Override
    public List<Permission> selectByUserId(Long userId) {
        return list("com.easy.admin.dao.PermissionDao.selectByUserId", userId);
    }
}
