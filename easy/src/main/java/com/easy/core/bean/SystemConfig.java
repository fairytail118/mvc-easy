/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.bean;

import java.io.Serializable;

/**
 * 系统配置
 * 
 * @author wy
 * @version v 0.1 2013-9-14 下午5:31:59 wy Exp $
 */
public class SystemConfig implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -778314181733191014L;

	/** 系统名称 */
	private String name;

	/** 网址地址 */
	private String domain;

	/** 系统版本 */
	private String version;

	/** 版权信息 */
	private String copyright;

	/** 系统说明 */
	private String description;

	/**
	 * @param name
	 * @param version
	 */
	public SystemConfig() {
	}

	/**
	 * @param name
	 * @param version
	 */
	public SystemConfig(String name, String version) {
		this.name = name;
		this.version = version;
	}

	/**
	 * Getter method for property <tt>name</tt>.
	 * 
	 * @return property value of name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter method for property <tt>name</tt>.
	 * 
	 * @param name
	 *            value to be assigned to property name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter method for property <tt>domain</tt>.
	 * 
	 * @return property value of domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * Setter method for property <tt>domain</tt>.
	 * 
	 * @param domain
	 *            value to be assigned to property domain
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * Getter method for property <tt>version</tt>.
	 * 
	 * @return property value of version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Setter method for property <tt>version</tt>.
	 * 
	 * @param version
	 *            value to be assigned to property version
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Getter method for property <tt>copyright</tt>.
	 * 
	 * @return property value of copyright
	 */
	public String getCopyright() {
		return copyright;
	}

	/**
	 * Setter method for property <tt>copyright</tt>.
	 * 
	 * @param copyright
	 *            value to be assigned to property copyright
	 */
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	/**
	 * Getter method for property <tt>description</tt>.
	 * 
	 * @return property value of description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter method for property <tt>description</tt>.
	 * 
	 * @param description
	 *            value to be assigned to property description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
