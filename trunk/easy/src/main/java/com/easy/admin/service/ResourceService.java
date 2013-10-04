/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.easy.admin.entity.Resource;
import com.easy.core.common.Page;

/**
 * 资源Service
 * 
 * @author wy
 * @version v 0.1 2013-10-04 上午 00:11:33 wy Exp $
 */
public interface ResourceService {

    /**
     * 保存管理员,根据ID判断是保存还是更新
     * 
     * @param resource
     * @return
     */
    public Resource save(Resource resource);

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
    public Resource getByPrimaryKey(Long id);

    /**
     * 分页查询
     * 
     * @param page
     */
    public void page(Page<Resource> page);

    /**
     * 检查url是否存在
     * 
     * @param url
     * @param id
     * @return
     */
    public boolean checkUrlExists(String url, Long id);

    /**
     * 所有的资源分组显示 freemarker循环，key必须为string
     * 
     * @return
     */
    public LinkedHashMap<String, List<Resource>> groupMapList();

}
