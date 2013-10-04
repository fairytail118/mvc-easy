/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.easy.admin.dao.ResourceDao;
import com.easy.admin.entity.Resource;
import com.easy.admin.enums.ResourceGroup;
import com.easy.admin.service.ResourceService;
import com.easy.core.common.Page;
import com.easy.core.enums.EnumsUtil;
import com.easy.core.exceptions.EasyException;

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
        resource.setIsSystem(false);
        //检查URL是否存在
        if (this.checkUrlExists(resource.getUrl(), resource.getId())) {
            throw new EasyException(resource.getUrl() + "已存在");
        }

        ResourceGroup resourceGroup = EnumsUtil.valueOf(ResourceGroup.class,
            resource.getGroupCode());

        if (resourceGroup == null) {
            throw new EasyException("找不到分组" + resource.getGroupCode());
        }

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

    /**
     * @see com.easy.admin.service.ResourceService#checkUrlExists(java.lang.String,
     *      java.lang.Long)
     */
    @Override
    public boolean checkUrlExists(String url, Long id) {
        url = getUrl(url);
        Resource resource = new Resource();
        resource.setUrl(url);
        List<Resource> list = resourceDao.selectByCriteria(resource);
        if (list.isEmpty()) {
            return false;
        }
        //如果没有id，但是存在
        else if (id == null) {
            return true;
        } else if (id.equals(list.get(0).getId())) {
            return false;
        }
        return true;
    }

    /**
     * 转换url
     * 
     * @param url
     * @return
     */
    private String getUrl(String url) {
        if (url.charAt(0) != '/') {
            return "/" + url;
        }
        return url;
    }

}
