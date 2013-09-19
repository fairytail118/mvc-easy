package com.easy.admin.entity;

import com.easy.core.entity.BaseEntity;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-19 8:21:50
 */
public class AllotRole extends BaseEntity {

	/** serialVersionUID */
	private static final long serialVersionUID = 2530969891345444650L;

	/** 用户ID */
	private Long userId;

	/** 角色ID */
	private Long roleId;

	/**
	 * Getter method for property <tt>userId</tt>.
	 * 
	 * @return property value of userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Setter method for property <tt>userId</tt>.
	 * 
	 * @param userId
	 *            value to be assigned to property userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Getter method for property <tt>roleId</tt>.
	 * 
	 * @return property value of roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * Setter method for property <tt>roleId</tt>.
	 * 
	 * @param roleId
	 *            value to be assigned to property roleId
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
