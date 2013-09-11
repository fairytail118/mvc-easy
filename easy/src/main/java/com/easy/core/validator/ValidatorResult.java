/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 验证结果
 * 
 * @author wy
 * @version v 0.1 2013-9-11 上午8:22:43 wy Exp $
 */
public class ValidatorResult implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -7817759150307607810L;

	/** 是否成功 */
	private boolean success = true;

	/** 验证器中的参数 */
	private final Map<String, String> validParam = new LinkedHashMap<String, String>();

	private String[] values;

	/**
	 * 消息
	 */
	private String message;

	/**
	 * 
	 * @param success
	 * @param values
	 */
	public ValidatorResult(boolean success, String[] values) {
		super();
		this.success = success;
		this.values = values;
	}
	

	/**
	 * @param success
	 * @param values
	 * @param message
	 */
	public ValidatorResult(boolean success, String[] values, String message) {
		super();
		this.success = success;
		this.values = values;
		this.message = message;
	}



	/**
	 * 设置参数
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public ValidatorResult putValidParam(String key, String value) {
		validParam.put(key, value);
		return this;
	}

	/**
	 * Getter method for property <tt>success</tt>.
	 * 
	 * @return property value of success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Setter method for property <tt>success</tt>.
	 * 
	 * @param success
	 *            value to be assigned to property success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * Getter method for property <tt>values</tt>.
	 * 
	 * @return property value of values
	 */
	public String[] getValues() {
		return values;
	}

	/**
	 * Setter method for property <tt>values</tt>.
	 * 
	 * @param values
	 *            value to be assigned to property values
	 */
	public void setValues(String[] values) {
		this.values = values;
	}

	/**
	 * Getter method for property <tt>validParam</tt>.
	 * 
	 * @return property value of validParam
	 */
	public Map<String, String> getValidParam() {
		return validParam;
	}

	/**
	 * Getter method for property <tt>message</tt>.
	 * 
	 * @return property value of message
	 */
	public String getMessage() {
		return message;
	}
	

}
