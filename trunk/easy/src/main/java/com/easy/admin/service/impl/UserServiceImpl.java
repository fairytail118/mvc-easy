/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.easy.admin.dao.UserDao;
import com.easy.admin.entity.User;
import com.easy.admin.service.UserService;
import com.easy.core.common.Page;
import com.easy.core.exceptions.EasyException;

/**
 * 用户ServiceImpl
 * 
 * @author wy
 * @version v 0.1 2013-9-3 下午11:23:27 wy Exp $
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao         userDao;

    @Value("${easy.permisson.prefix}")
    private String          prefixPermission;

    @Resource
    private SaltSource      saltSource;

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * @see com.easy.admin.service.UserService#save(com.easy.admin.entity.User)
     */
    @Override
    public User save(User user) {

        if (StringUtils.isNotBlank(user.getPassword())) {
            user.setPassword(encodePassword(user.getUsername(), user));
        }

        if (user.getId() == null) {
            //检查用户名是否存在
            if (this.checkUsernameExists(user.getUsername())) {
                throw new EasyException("用户名已经存在!");
            }

            userDao.create(user);
        } else {
            user.setUsername(null);
            userDao.update(user);
        }
        return user;
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

    /**
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,
                                                          DataAccessException {

        User user = userDao.loadUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户[" + username + "]不存在!");
        }
        //        //查询用户的权限
        //        List<Permission> list = permissionDao.selectByUserId(user.getId());
        //
        //        //security 权限
        //        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        //
        //        for (Permission p : list) {
        //            authorities.add(new GrantedAuthorityImpl(SecurityUtil.getPermission(prefixPermission,
        //                p.getCode())));
        //        }

        //user.setAuthorities(authorities);

        return user;
    }

    /**
     * @see com.easy.admin.service.UserService#updatePassowrd(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    @Override
    public void updatePassowrd(String username, String newPassword, String password) {
        User user = userDao.loadUserByUsername(username);

        if (user == null) {
            throw new EasyException("用户[" + username + "]不存在!");
        }

        //加密
        boolean isValid = passwordEncoder.isPasswordValid(user.getPassword(), password,
            saltSource.getSalt(user));
        if (!isValid) {
            throw new EasyException("原密码不正确");
        }
        //密码
        user.setPassword(encodePassword(newPassword, user));
        userDao.update(user);
    }

    /**
     * @see com.easy.admin.service.UserService#switchLock(java.lang.Long)
     */
    @Override
    public void switchLock(Long id) {
        User user = userDao.getByPrimaryKey(id);
        if (user == null) {
            throw new EasyException("找不到需要锁定的用户");
        }

        user.setIsLocked(BooleanUtils.isFalse(user.getIsLocked()));
        user.setModifyTime(null);
        user.setModifyUser(null);

        userDao.update(user);

    }

    /**
     * @see com.easy.admin.service.UserService#switchEnable(java.lang.Long)
     */
    @Override
    public void switchEnable(Long id) {
        User user = userDao.getByPrimaryKey(id);
        if (user == null) {
            throw new EasyException("找不到需要启用禁用的用户");
        }

        user.setIsEnabled(BooleanUtils.isFalse(user.getIsEnabled()));
        user.setModifyTime(null);
        user.setModifyUser(null);

        userDao.update(user);
    }

    /**
     * @see com.easy.admin.service.UserService#checkUsernameExists(java.lang.String)
     */
    @Override
    public boolean checkUsernameExists(String username) {
        User user = new User();
        user.setUsername(username);
        return userDao.countByCriteria(user) > 0;
    }

    /**
     * 加密
     * 
     * @param newPassword
     * @param user
     * @return
     */
    private String encodePassword(String newPassword, User user) {
        return passwordEncoder.encodePassword(newPassword, saltSource.getSalt(user));
    }

}
