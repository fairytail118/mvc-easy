/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 查询条件
 * 
 * @author wy
 * @version v 0.1 2013-9-2 下午8:49:51 wy Exp $
 */
public class Criteria implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 2270303858080798787L;

	/** 默认的分页大小 */
	public transient static final int DEFAULT_PAGE_SIZE = 20;

	/** 查询参数 */
	protected transient final Map<String, Object> criteria = new HashMap<String, Object>();

	/** 是否相异 */
	protected transient boolean distinct;

	/** 排序字段 */
	protected transient String orderByClause;

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

	/**
	 * 清除查询条件
	 * 
	 * @see java.util.Map#clear()
	 */
	public void clear() {
		criteria.clear();
	}

	/**
	 * 判断是否包含某个key
	 * 
	 * @param key
	 * @return
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	public boolean containsKey(Object key) {
		return criteria.containsKey(key);
	}

	/**
	 * 判断是否包含某个value
	 * 
	 * @param value
	 * @return
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	public boolean containsValue(Object value) {
		return criteria.containsValue(value);
	}

	/**
	 * 获取某个对象
	 * 
	 * @param key
	 * @return
	 * @see java.util.Map#get(java.lang.Object)
	 */
	public Object get(Object key) {
		return criteria.get(key);
	}

	/**
	 * 查询条件是否为空
	 * 
	 * @return
	 * @see java.util.Map#isEmpty()
	 */
	public boolean isEmpty() {
		return criteria.isEmpty();
	}

	/**
	 * 设置查询条件
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	public Object put(String key, Object value) {
		return criteria.put(key, value);
	}

	/**
	 * 移除某个key
	 * 
	 * @param key
	 * @return
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	public Object remove(Object key) {
		return criteria.remove(key);
	}

}
