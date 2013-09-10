/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.entity;

import java.util.HashMap;

/**
 * 查询条件
 * 
 * @author wy
 * @version v 0.1 2013-9-2 下午8:49:51 wy Exp $
 */
public class Criteria extends HashMap<String, Object> {

    /** serialVersionUID */
    private static final long                               serialVersionUID  = 2270303858080798787L;

    /** 默认的分页大小 */
    public static final int                                 DEFAULT_PAGE_SIZE = 20;

    /** 是否相异 */
    protected transient boolean                             distinct;

    /** 排序字段 */
    protected transient String                              orderByClause;

    /**
     * Getter method for property <tt>distinct</tt>.
     * 
     * @return property value of distinct
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * Setter method for property <tt>distinct</tt>.
     * 
     * @param distinct
     *            value to be assigned to property distinct
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * Getter method for property <tt>orderByClause</tt>.
     * 
     * @return property value of orderByClause
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * Setter method for property <tt>orderByClause</tt>.
     * 
     * @param orderByClause
     *            value to be assigned to property orderByClause
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }
    
}
