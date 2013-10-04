/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.easy.admin.dao.RoleDao;
import com.easy.admin.dao.UserDao;
import com.easy.admin.dao.UserRoleDao;
import com.easy.admin.entity.Role;
import com.easy.admin.entity.User;
import com.easy.admin.entity.UserRole;
import com.easy.admin.service.UserService;
import com.easy.core.common.Page;
import com.easy.core.exceptions.EasyException;
import com.easy.core.security.util.SecurityUtil;

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

    @Resource
    private RoleDao         roleDao;

    @Resource
    private UserRoleDao     userRoleDao;

    @Resource
    private SaltSource      saltSource;

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * @see com.easy.admin.service.UserService#save(com.easy.admin.entity.User,java.util.List)
     */
    @Override
    public User save(User user, List<Long> roleList) {

        if (StringUtils.isNotBlank(user.getPassword())) {
            user.setPassword(encodePassword(user.getPassword(), user));
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
            userRoleDao.deleteByPrimaryKeys(user.getId(), null);
        }

        if (roleList != null) {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            for (Long roleId : roleList) {
                userRole.setRoleId(roleId);
                userRoleDao.create(userRole);
            }
        }

        return user;
    }

    /**
     * @see com.easy.admin.service.UserService#getByPrimaryKey(java.lang.Long)
     */
    @Override
    public User getByPrimaryKey(Long id) {

        User user = userDao.getByPrimaryKey(id);

        if (user != null) {
            //查询用户的角色
            List<Role> list = roleDao.selectByUserId(user.getId());

            List<Long> roleList = new ArrayList<Long>();

            for (Role role : list) {
                roleList.add(role.getId());
            }
            user.put("roleList", roleList);
        }

        return user;
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
        //查询用户的角色
        List<Role> list = roleDao.selectByUserId(user.getId());

        //security 角色
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

        for (Role role : list) {
            authorities.add(new GrantedAuthorityImpl(SecurityUtil.getRole(role.getCode())));
        }

        user.setAuthorities(authorities);

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
