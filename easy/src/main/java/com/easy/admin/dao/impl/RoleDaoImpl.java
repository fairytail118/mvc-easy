/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.easy.admin.dao.RoleDao;
import com.easy.admin.entity.Role;
import org.apache.ibatis.session.RowBounds;
import com.easy.core.common.Page;
import com.easy.core.dao.impl.MyBatisGenericDao;

/**
 * 角色DaoImpl
 * 
 * @author wy
 * @version v 0.1 13-09-23 下午 20:32:33 wy Exp $
 */
@Repository
public class RoleDaoImpl extends MyBatisGenericDao implements RoleDao {

    /**
     * @see com.easy.admin.dao.RoleDao#create(com.easy.admin.entity.Role)
     */
    @Override
    public Role create(Role role) {
        save("com.easy.admin.dao.RoleDao.create", role);
        return role;
    }

    /**
     * @see com.easy.admin.dao.RoleDao#update(com.easy.admin.entity.Role)
     */
    @Override
    public int update(Role role) {
        return update("com.easy.admin.dao.RoleDao.update", role);
    }

    /**
     * @see com.easy.admin.dao.RoleDao#deleteByPrimaryKeys(java.lang.Long[])
     */
    @Override
    public int deleteByPrimaryKeys(Long... ids) {
        return delete("com.easy.admin.dao.RoleDao.deleteByPrimaryKeys", ids);
    }

    /**
     * @see com.easy.admin.dao.RoleDao#getByPrimaryKey(java.lang.Long)
     */
    @Override
    public Role getByPrimaryKey(Long id) {
        return get("com.easy.admin.dao.RoleDao.getByPrimaryKey", id);
    }

    /**
     * @see com.easy.admin.dao.RoleDao#selectByCriteria(com.easy.admin.entity.Role)
     */
    @Override
    public List<Role> selectByCriteria(Role role) {
        return list("com.easy.admin.dao.RoleDao.selectByCriteria", role);
    }

    /**
     * @see com.easy.admin.dao.RoleDao#countByCriteria(com.easy.admin.entity.Role)
     */
    @Override
    public int countByCriteria(Role role) {
        return get("com.easy.admin.dao.RoleDao.countByCriteria", role);
    }

    /**
     * @see com.easy.admin.dao.RoleDao#page(com.easy.core.common.Page)
     */
    @Override
    public void page(Page<Role> page) {
        RowBounds rowBounds = new RowBounds(page.getOffset(), page.getPageSize());
        List<Role> list = list("com.easy.admin.dao.RoleDao.selectByCriteria", page.getCriteria(),
            rowBounds);
        page.setList(list);
        page.setTotalCount(countByCriteria(page.getCriteria()));
    }

    /**
     * @see com.easy.admin.dao.RoleDao#selectByUserId(java.lang.Long)
     */
    @Override
    public List<Role> selectByUserId(Long userId) {
        return list("com.easy.admin.dao.RoleDao.selectByUserId",userId);
    }
}
