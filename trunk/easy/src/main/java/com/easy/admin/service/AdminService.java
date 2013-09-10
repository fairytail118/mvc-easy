/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.service;

import com.easy.admin.entity.Admin;
import com.easy.core.common.Page;

/**
 * 管理员Service
 * 
 * @author wy
 * @version v 0.1 2013-9-3 下午11:18:20 wy Exp $
 */
public interface AdminService {
    
    /**
     * 保存管理员,根据ID判断是保存还是更新
     * 
     * @param admin
     * @return 
     */
    public Admin save(Admin admin);

    /**
     * 根据主键删除
     * 
     * @param ids
     * @return
     */
    public void deleteByPrimaryKeys(Long[] keys);

    /**
     * 根据主键查询
     * 
     * @param id
     * @return
     */
    public Admin getByPrimaryKey(Long id);

    /**
     * 分页查询
     * 
     * @param page
     */
    public void page(Page<Admin> page);
    

}
