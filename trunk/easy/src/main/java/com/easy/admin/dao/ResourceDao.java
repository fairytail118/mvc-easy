/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.dao;

import java.util.List;

import com.easy.admin.entity.Resource;
import com.easy.core.common.Page;

/**
 * 资源Dao
 * 
 * @author wy
 * @version v 0.1 2013-10-04 上午 00:11:33 wy Exp $
 */
public interface ResourceDao {

    /**
     * 保存
     * 
     * @param resource
     * @return
     */
    public Resource create(Resource resource);

    /**
     * 更新
     * 
     * @param resource
     * @param 返回更新数量
     */
    public int update(Resource resource);

    /**
     * 根据主键删除
     * 
     * @param ids 
     * @return
     */
    public int deleteByPrimaryKeys(Long...ids );

    /**
     * 根据主键查询
     * 
     * @param  id 
     * @return
     */
    public Resource getByPrimaryKey(Long id );

    /**
     * 条件查询列表
     * 
     * @param resource
     * @return
     */
    public List<Resource> selectByCriteria(Resource resource);

    /**
     * 条件查询数量
     * 
     * @param resource
     * @return
     */
    public int countByCriteria(Resource resource);

    /**
     * 分页查询
     * 
     * @param page
     */
    public void page(Page<Resource> page);

}
