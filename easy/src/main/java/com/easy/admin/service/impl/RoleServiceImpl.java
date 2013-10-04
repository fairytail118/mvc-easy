/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.stereotype.Service;

import com.easy.admin.dao.RoleDao;
import com.easy.admin.dao.RoleResourceDao;
import com.easy.admin.entity.Role;
import com.easy.admin.entity.RoleResource;
import com.easy.admin.service.RoleService;
import com.easy.core.common.Page;
import com.easy.core.exceptions.EasyException;

/**
 * 角色ServiceImpl
 * 
 * @author wy
 * @version v 0.1 2013-9-3 下午11:23:27 wy Exp $
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao         roleDao;

    @Resource
    private RoleResourceDao roleResourceDao;

    /**
     * @see com.easy.admin.service.RoleService#save(com.easy.admin.entity.Role,
     *      java.util.List)
     */
    @Override
    public Role save(Role role, List<Long> resourceList) {
        if (this.checkCodeExists(role.getCode(), role.getId())) {
            throw new EasyException("编码已经存在!");
        }
        role.setIsSystem(false);
        if (role.getId() == null) {
            roleDao.create(role);
        } else {
            Role entity = roleDao.getByPrimaryKey(role.getId());
            if (entity == null) {
                throw new EasyException("找不到Role[" + role.getId() + "]");
            } else if (BooleanUtils.isTrue(entity.getIsSystem())) {
                throw new EasyException("Role[" + role.getId() + "].IsSystem为true,不能修改");
            }
            roleDao.update(role);
            roleResourceDao.deleteByPrimaryKeys(role.getId(), null);
        }

        if (resourceList != null) {
            RoleResource reoleResource = new RoleResource();
            for (Long resourceId : resourceList) {
                reoleResource.setResourceId(resourceId);
                reoleResource.setRoleId(role.getId());
                roleResourceDao.create(reoleResource);
            }
        }
        return role;
    }

    /**
     * @see com.easy.admin.service.RoleService#deleteByPrimaryKeys(java.lang.Long[])
     */
    @Override
    public void deleteByPrimaryKeys(java.lang.Long[] keys) {
        roleDao.deleteByPrimaryKeys(keys);
    }

    /**
     * @see com.easy.admin.service.RoleService#getByPrimaryKey(java.lang.Long)
     */
    @Override
    public Role getByPrimaryKey(Long id) {

        Role role = roleDao.getByPrimaryKey(id);

        if (role != null) {
            RoleResource roleResource = new RoleResource();
            roleResource.setRoleId(role.getId());

            List<RoleResource> list = roleResourceDao.selectByCriteria(roleResource);
            List<Long> resourceList = new ArrayList<Long>(list.size());

            for (RoleResource resource : list) {
                resourceList.add(resource.getResourceId());
            }

            role.put("resourceList", resourceList);
        }

        return role;
    }

    /**
     * @see com.easy.admin.service.RoleService#page(com.easy.core.common.Page)
     */
    @Override
    public void page(Page<Role> page) {
        roleDao.page(page);
    }

    /**
     * @see com.easy.admin.service.RoleService#checkCodeExists(java.lang.String,
     *      java.lang.Long)
     */
    @Override
    public boolean checkCodeExists(String code, Long id) {
        Role role = new Role();
        role.setCode(code);
        List<Role> list = roleDao.selectByCriteria(role);
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
     * @see com.easy.admin.service.RoleService#roleList()
     */
    @Override
    public List<Role> roleList() {
        Role role = new Role();
        return roleDao.selectByCriteria(role);
    }

}
