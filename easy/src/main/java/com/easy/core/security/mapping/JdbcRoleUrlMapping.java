/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.security.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.util.Assert;

import com.easy.admin.entity.Role;
import com.easy.admin.service.ResourceService;
import com.easy.core.security.util.SecurityUtil;

/**
 * 从数据库中加载角色url映射关系
 * 
 * @author wy
 * @version v 0.1 2013-10-4 下午1:58:17 wy Exp $
 */
public class JdbcRoleUrlMapping implements RoleUrlMapping, InitializingBean {

    /** 日志 */
    private final static Logger                             log                    = LoggerFactory
                                                                                       .getLogger(JdbcRoleUrlMapping.class);

    /** url对应的权限列表 */
    private final static Map<String, List<ConfigAttribute>> URL_ROLE_MAPPING       = new ConcurrentHashMap<String, List<ConfigAttribute>>();

    /** 是否更新 */
    private final static AtomicBoolean                      SHOULD_UPDATE_RESOURCE = new AtomicBoolean(
                                                                                       true);
    /** 资源service */
    private ResourceService                                 resourceService;

    /**
     * 设置需要更新
     */
    public static void shouldUpdate() {
        log.debug("需要更新系统的url和角色的映射关系...");
        if (!SHOULD_UPDATE_RESOURCE.get()) {
            SHOULD_UPDATE_RESOURCE.set(true);
        }
    }

    /**
     * @see com.easy.core.security.mapping.RoleUrlMapping#getAllMapping()
     */
    @Override
    public Map<String, List<ConfigAttribute>> getAllMapping() {
        if (SHOULD_UPDATE_RESOURCE.get()) {
            updateRoleMap();
        }
        return URL_ROLE_MAPPING;
    }

    /**
     * 更新对应关系
     */
    private synchronized void updateRoleMap() {
        if (SHOULD_UPDATE_RESOURCE.get()) {
            log.debug("正在更新系统的url和角色的映射关系...");
            Map<String, List<Role>> map = resourceService.resourceRoleMap();
            for (String key : map.keySet()) {
                List<Role> roleList = map.get(key);
                if (roleList.isEmpty()) {
                    continue;
                }

                String resUrl = SecurityUtil.getUrl(key);
                List<ConfigAttribute> list = URL_ROLE_MAPPING.get(resUrl);
                if (list == null) {
                    list = new ArrayList<ConfigAttribute>();
                    URL_ROLE_MAPPING.put(resUrl, list);
                }
                for (Role role : roleList) {
                    SecurityConfig securityConfig = new SecurityConfig(SecurityUtil.getRole(role
                        .getCode()));
                    if (!list.contains(securityConfig)) {
                        list.add(securityConfig);
                    }
                }
            }
            SHOULD_UPDATE_RESOURCE.set(false);
        }
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(resourceService, "resourceService不能为空");
    }

    /**
     * Setter method for property <tt>resourceService</tt>.
     * 
     * @param resourceService value to be assigned to property resourceService
     */
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

}
