/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.easy.admin.dao.UserDao;
import com.easy.admin.entity.User;
import org.apache.ibatis.session.RowBounds;
import com.easy.core.common.Page;
import com.easy.core.dao.impl.MyBatisGenericDao;

/**
 * 用户DaoImpl
 * 
 * @author wy
 * @version v 0.1 13-09-23 下午 20:32:31 wy Exp $
 */
@Repository
public class UserDaoImpl extends MyBatisGenericDao implements UserDao {

    /**
     * @see com.easy.admin.dao.UserDao#create(com.easy.admin.entity.User)
     */
    @Override
    public User create(User user) {
        save("com.easy.admin.dao.UserDao.create", user);
        return user;
    }

    /**
     * @see com.easy.admin.dao.UserDao#update(com.easy.admin.entity.User)
     */
    @Override
    public int update(User user) {
        return update("com.easy.admin.dao.UserDao.update", user);
    }

    /**
     * @see com.easy.admin.dao.UserDao#deleteByPrimaryKeys(java.lang.Long[])
     */
    @Override
    public int deleteByPrimaryKeys(Long... ids) {
        return delete("com.easy.admin.dao.UserDao.deleteByPrimaryKeys", ids);
    }

    /**
     * @see com.easy.admin.dao.UserDao#getByPrimaryKey(java.lang.Long)
     */
    @Override
    public User getByPrimaryKey(Long id) {
        return get("com.easy.admin.dao.UserDao.getByPrimaryKey", id);
    }

    /**
     * @see com.easy.admin.dao.UserDao#selectByCriteria(com.easy.admin.entity.User)
     */
    @Override
    public List<User> selectByCriteria(User user) {
        return list("com.easy.admin.dao.UserDao.selectByCriteria", user);
    }

    /**
     * @see com.easy.admin.dao.UserDao#countByCriteria(com.easy.admin.entity.User)
     */
    @Override
    public int countByCriteria(User user) {
        return get("com.easy.admin.dao.UserDao.countByCriteria", user);
    }

    /**
     * @see com.easy.admin.dao.UserDao#page(com.easy.core.common.Page)
     */
    @Override
    public void page(Page<User> page) {
        RowBounds rowBounds = new RowBounds(page.getOffset(), page.getPageSize());
        List<User> list = list("com.easy.admin.dao.UserDao.selectByCriteria", page.getCriteria(),
            rowBounds);
        page.setList(list);
        page.setTotalCount(countByCriteria(page.getCriteria()));
    }

    /**
     * @see com.easy.admin.dao.UserDao#loadUserByUsername(java.lang.String)
     */
    @Override
    public User loadUserByUsername(String username) {
        return get("com.easy.admin.dao.UserDao.loadUserByUsername", username);
    }
}
