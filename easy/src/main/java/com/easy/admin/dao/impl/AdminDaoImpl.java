/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.dao.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.easy.admin.dao.AdminDao;
import com.easy.admin.entity.Admin;
import com.easy.core.common.Page;
import com.easy.core.dao.impl.MyBatisGenericDao;

/**
 * 管理员DaoImpl
 * 
 * @author wy
 * @version v 0.1 2013-9-2 下午10:58:40 wy Exp $
 */
@Repository
public class AdminDaoImpl extends MyBatisGenericDao implements AdminDao {

    /**
     * @see com.easy.admin.dao.AdminDao#create(com.easy.admin.entity.Admin)
     */
    @Override
    public long create(Admin admin) {
        save("com.easy.dao.AdminDao.create", admin);
        return admin.getId();
    }

    /**
     * @see com.easy.admin.dao.AdminDao#update(com.easy.admin.entity.Admin)
     */
    @Override
    public int update(Admin admin) {
        return update("com.easy.dao.AdminDao.update", admin);
    }

    /**
     * @see com.easy.admin.dao.AdminDao#deleteByPrimaryKeys(java.lang.Long[])
     */
    @Override
    public int deleteByPrimaryKeys(Long... ids) {
        return delete("com.easy.dao.AdminDao.deleteByPrimaryKeys", ids);
    }

    /**
     * @see com.easy.admin.dao.AdminDao#getByPrimaryKey(java.lang.Long)
     */
    @Override
    public Admin getByPrimaryKey(Long id) {
        return get("com.easy.dao.AdminDao.getByPrimaryKey", id);
    }

    /**
     * @see com.easy.admin.dao.AdminDao#selectByCriteria(com.easy.admin.entity.Admin)
     */
    @Override
    public List<Admin> selectByCriteria(Admin admin) {
        return list("com.easy.dao.AdminDao.selectByCriteria", admin);
    }

    /**
     * @see com.easy.admin.dao.AdminDao#countByCriteria(com.easy.admin.entity.Admin)
     */
    @Override
    public int countByCriteria(Admin admin) {
        return get("com.easy.dao.AdminDao.countByCriteria", admin);
    }

    /**
     * @see com.easy.admin.dao.AdminDao#page(com.easy.core.common.Page)
     */
    @Override
    public void page(Page<Admin> page) {
        RowBounds rowBounds = new RowBounds(page.getOffset(), page.getPageSize());
        List<Admin> list = list("com.easy.dao.AdminDao.selectByCriteria", page.getCriteria(),
            rowBounds);
        page.setList(list);
        page.setTotalCount(countByCriteria(page.getCriteria()));
    }
}
