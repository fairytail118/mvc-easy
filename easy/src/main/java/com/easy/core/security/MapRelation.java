/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.security;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;

/**
 * url映射对应关系
 * 
 * @author wy
 * @version v 0.1 2013-9-25 下午9:51:39 wy Exp $
 */
public interface MapRelation {

    /**
     * 获取所有的映射关系
     * 
     * @return
     */
    public Map<String, List<ConfigAttribute>> getAllMapRelation();

}
