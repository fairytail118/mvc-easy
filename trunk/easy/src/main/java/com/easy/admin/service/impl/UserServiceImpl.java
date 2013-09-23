/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.easy.admin.dao.UserDao;
import com.easy.admin.entity.User;
import com.easy.admin.service.UserService;
import com.easy.core.common.Page;

/**
 * 用户ServiceImpl
 * 
 * @author wy
 * @version v 0.1 2013-9-3 下午11:23:27 wy Exp $
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    /**
     * @see com.easy.admin.service.UserService#save(com.easy.admin.entity.User)
     */
    @Override
    public User save(User user) {
        if (user.getId() == null) {
            userDao.create(user);
        } else {
            userDao.update(user);
        }
        return user;
    }

    /**
     * @see com.easy.admin.service.UserService#deleteByPrimaryKeys(java.lang.Long[])
     */
    @Override
    public void deleteByPrimaryKeys(java.lang.Long[] keys) {
        userDao.deleteByPrimaryKeys(keys);
    }

    /**
     * @see com.easy.admin.service.UserService#getByPrimaryKey(java.lang.Long)
     */
    @Override
    public User getByPrimaryKey(Long id) {

        return userDao.getByPrimaryKey(id);
    }

    /**
     * @see com.easy.admin.service.UserService#page(com.easy.core.common.Page)
     */
    @Override
    public void page(Page<User> page) {
        userDao.page(page);
    }

}
