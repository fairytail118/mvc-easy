/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.service.impl;

import org.springframework.stereotype.Service;

import com.easy.admin.dao.ResourceDao;
import com.easy.admin.entity.Resource;
import com.easy.admin.service.ResourceService;
import com.easy.core.common.Page;

/**
 * 资源ServiceImpl
 * 
 * @author wy
 * @version v 0.1 2013-9-3 下午11:23:27 wy Exp $
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @javax.annotation.Resource
    private ResourceDao resourceDao;

    /**
     * @see com.easy.admin.service.ResourceService#save(com.easy.admin.entity.Resource)
     */
    @Override
    public Resource save(Resource resource) {
        if (resource.getId() == null) {
            resourceDao.create(resource);
        } else {
            resourceDao.update(resource);
        }
        return resource;
    }

    /**
     * @see com.easy.admin.service.ResourceService#deleteByPrimaryKeys(java.lang.Long[])
     */
    @Override
    public void deleteByPrimaryKeys(java.lang.Long[] keys) {
        resourceDao.deleteByPrimaryKeys(keys);
    }

    /**
     * @see com.easy.admin.service.ResourceService#getByPrimaryKey(java.lang.Long)
     */
    @Override
    public Resource getByPrimaryKey(Long id) {

        return resourceDao.getByPrimaryKey(id);
    }

    /**
     * @see com.easy.admin.service.ResourceService#page(com.easy.core.common.Page)
     */
    @Override
    public void page(Page<Resource> page) {
        resourceDao.page(page);
    }

}
