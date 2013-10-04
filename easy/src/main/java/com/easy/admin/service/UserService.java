/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.easy.admin.entity.User;
import com.easy.core.common.Page;

/**
 * 用户Service
 * 
 * @author wy
 * @version v 0.1 13-09-23 下午 20:32:32 wy Exp $
 */
public interface UserService extends UserDetailsService {

    /**
     * 保存管理员,根据ID判断是保存还是更新
     * 
     * @param user
     * @param roleList
     * @return
     */
    public User save(User user, List<Long> roleList);

    /**
     * 根据主键查询
     * 
     * @param id
     * @return
     */
    public User getByPrimaryKey(Long id);

    /**
     * 分页查询
     * 
     * @param page
     */
    public void page(Page<User> page);

    /**
     * 修改密码
     * 
     * @param username 用户名
     * @param newPassword 新密码
     * @param password 原来密码
     */
    public void updatePassowrd(String username, String newPassword, String password);

    /**
     * 切换锁定状态
     * 
     * @param id
     */
    public void switchLock(Long id);

    /**
     * 切换启用禁用状态
     * 
     * @param id
     */
    public void switchEnable(Long id);

    /**
     * 检查用户名是否存在，只有新增才验证，更新不变更用户名
     * 
     * @param username
     * @return
     */
    public boolean checkUsernameExists(String username);

}
