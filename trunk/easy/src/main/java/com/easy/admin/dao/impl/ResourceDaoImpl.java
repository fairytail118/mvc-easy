/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.easy.admin.dao.ResourceDao;
import com.easy.admin.entity.Resource;
import org.apache.ibatis.session.RowBounds;
import com.easy.core.common.Page;
import com.easy.core.dao.impl.MyBatisGenericDao;

/**
 * 资源DaoImpl
 * 
 * @author wy
 * @version v 0.1 2013-10-04 上午 00:11:33 wy Exp $
 */
@Repository
public class ResourceDaoImpl extends MyBatisGenericDao implements ResourceDao {

    /**
     * @see com.easy.admin.dao.ResourceDao#create(com.easy.admin.entity.Resource)
     */
    @Override
    public Resource create(Resource resource) {
        save("com.easy.admin.dao.ResourceDao.create", resource);
        return resource;
    }

    /**
     * @see com.easy.admin.dao.ResourceDao#update(com.easy.admin.entity.Resource)
     */
    @Override
    public int update(Resource resource) {
        return update("com.easy.admin.dao.ResourceDao.update", resource);
    }

    /**
     * @see com.easy.admin.dao.ResourceDao#deleteByPrimaryKeys(java.lang.Long[])
     */
    @Override
    public int deleteByPrimaryKeys(Long... ids) {
        return delete("com.easy.admin.dao.ResourceDao.deleteByPrimaryKeys", ids);
    }

    /**
     * @see com.easy.admin.dao.ResourceDao#getByPrimaryKey(java.lang.Long)
     */
    @Override
    public Resource getByPrimaryKey(Long id) {
        return get("com.easy.admin.dao.ResourceDao.getByPrimaryKey", id);
    }

    /**
     * @see com.easy.admin.dao.ResourceDao#selectByCriteria(com.easy.admin.entity.Resource)
     */
    @Override
    public List<Resource> selectByCriteria(Resource resource) {
        return list("com.easy.admin.dao.ResourceDao.selectByCriteria", resource);
    }

    /**
     * @see com.easy.admin.dao.ResourceDao#countByCriteria(com.easy.admin.entity.Resource)
     */
    @Override
    public int countByCriteria(Resource resource) {
        return get("com.easy.admin.dao.ResourceDao.countByCriteria", resource);
    }

    /**
     * @see com.easy.admin.dao.ResourceDao#page(com.easy.core.common.Page)
     */
    @Override
    public void page(Page<Resource> page) {
        RowBounds rowBounds = new RowBounds(page.getOffset(), page.getPageSize());
        List<Resource> list = list("com.easy.admin.dao.ResourceDao.selectByCriteria",
            page.getCriteria(), rowBounds);
        page.setList(list);
        page.setTotalCount(countByCriteria(page.getCriteria()));
    }
}
