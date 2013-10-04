/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.stereotype.Service;

import com.easy.admin.dao.ResourceDao;
import com.easy.admin.dao.RoleDao;
import com.easy.admin.entity.Resource;
import com.easy.admin.entity.Role;
import com.easy.admin.enums.ResourceGroup;
import com.easy.admin.service.ResourceService;
import com.easy.core.common.Page;
import com.easy.core.enums.EnumsUtil;
import com.easy.core.exceptions.EasyException;
import com.easy.core.security.mapping.JdbcRoleUrlMapping;
import com.easy.core.security.util.SecurityUtil;

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

    @javax.annotation.Resource
    private RoleDao     roleDao;

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
            Resource entity = resourceDao.getByPrimaryKey(resource.getId());
            if (entity == null) {
                throw new EasyException("找不到Resource[" + resource.getId() + "]");
            } else if (BooleanUtils.isTrue(entity.getIsSystem())) {
                throw new EasyException("Resource[" + resource.getId() + "].IsSystem为true,不能修改");
            }
            resourceDao.update(resource);
        }
        JdbcRoleUrlMapping.shouldUpdate();
        return resource;
    }

    /**
     * @see com.easy.admin.service.ResourceService#deleteByPrimaryKeys(java.lang.Long[])
     */
    @Override
    public void deleteByPrimaryKeys(Long[] keys) {
        resourceDao.deleteByPrimaryKeys(keys);
        JdbcRoleUrlMapping.shouldUpdate();
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
        url = SecurityUtil.getUrl(url);
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
     * @see com.easy.admin.service.ResourceService#groupMapList()
     */
    @Override
    public LinkedHashMap<String, List<Resource>> groupMapList() {

        LinkedHashMap<String, List<Resource>> groupMap = new LinkedHashMap<String, List<Resource>>();

        Resource resource = new Resource();

        List<Resource> list = null;
        for (ResourceGroup resourceGroup : ResourceGroup.values()) {
            resource.setGroupCode(resourceGroup.getCode());
            list = resourceDao.selectByCriteria(resource);
            if (list.isEmpty()) {
                continue;
            }
            groupMap.put(resourceGroup.getCode(), list);

        }

        return groupMap;
    }

    /**
     * @see com.easy.admin.service.ResourceService#resourceRoleMap()
     */
    @Override
    public Map<String, List<Role>> resourceRoleMap() {

        Resource resource = new Resource();
        Map<String, List<Role>> map = new HashMap<String, List<Role>>();
        List<Resource> list = resourceDao.selectByCriteria(resource);

        for (Resource r : list) {
            map.put(r.getUrl(), roleDao.selectByResourceId(r.getId()));
        }

        return map;
    }

}
