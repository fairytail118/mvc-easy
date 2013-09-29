/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.service;

import java.util.List;

import com.easy.admin.entity.Permission;
import com.easy.core.common.Page;

/**
 * 权限Service
 * 
 * @author wy
 * @version v 0.1 13-09-23 下午 20:32:33 wy Exp $
 */
public interface PermissionService {

    /**
     * 保存管理员,根据ID判断是保存还是更新
     * 
     * @param permission
     * @return
     */
    public Permission save(Permission permission);

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
     * @param id
     * @return
     */
    public Permission getByPrimaryKey(Long id);

    /**
     * 分页查询
     * 
     * @param page
     */
    public void page(Page<Permission> page);

    /**
     * 第一等级的权限
     * 
     * @return
     */
    public List<Permission> firstPermissionList();

    /**
     * 检查编码是否存在
     * 
     * @param code
     * @param id
     * @return
     */
    public boolean checkCodeExists(String code,Long id);

}
