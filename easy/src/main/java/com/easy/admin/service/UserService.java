/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.easy.admin.entity.User;
import com.easy.core.common.Page;

/**
 * 用户Service
 * 
 * @author wy
 * @version v 0.1 13-09-23 下午 20:32:32 wy Exp $
 */
public interface UserService  extends UserDetailsService{
    
    /**
     * 保存管理员,根据ID判断是保存还是更新
     * 
     * @param user
     * @return 
     */
    public User save(User user);

    /**
     * 根据主键删除
     * 
     * @param keys
     * @return
     */
    public void deleteByPrimaryKeys(Long[] keys);

    /**
     * 根据主键查询
     * 
     * @param  id 
          * @return
     */
    public User getByPrimaryKey(Long id);

    /**
     * 分页查询
     * 
     * @param page
     */
    public void page(Page<User> page);
    

}
