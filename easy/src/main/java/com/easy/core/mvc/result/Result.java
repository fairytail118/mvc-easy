/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.mvc.result;

import java.io.Serializable;

import com.easy.core.exceptions.ExceptionUtil;

/**
 * json返回结果
 * 
 * @author wy
 * @version v 0.1 2013-6-17 下午9:04:03 wy Exp $
 */
public class Result implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -4135003009539724018L;

    /** 执行结果状态 */
    private boolean           success          = true;

    /** 权限方面的问题 */
    private boolean           denied           = false;

    /** 错误结果信息 */
    private String            message;

    /** 返回结果 */
    private Object            data;

    /**
     * 
     */
    public Result() {

    }

    /**
     * 返回错误结果
     * 
     * @param message
     * @return
     */
    public Result(String message) {
        this.success = false;
        this.message = message;
    }

    /**
     * 返回错误结果
     * 
     * @param e
     */
    public Result(Exception e) {
        this.success = false;
        this.message = ExceptionUtil.getResultMessage(e);
    }

    /**
     * 返回结果
     * 
     * @param success
     * @param message
     * @return
     */
    public Result(boolean success, Object data) {
        this.success = success;
        this.data = data;
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
     * Getter method for property <tt>message</tt>.
     * 
     * @return property value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter method for property <tt>message</tt>.
     * 
     * @param message
     *            value to be assigned to property message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Getter method for property <tt>data</tt>.
     * 
     * @return property value of data
     */
    public Object getData() {
        return data;
    }

    /**
     * Setter method for property <tt>data</tt>.
     * 
     * @param data
     *            value to be assigned to property data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * Getter method for property <tt>denied</tt>.
     * 
     * @return property value of denied
     */
    public boolean isDenied() {
        return denied;
    }

    /**
     * Setter method for property <tt>denied</tt>.
     * 
     * @param denied
     *            value to be assigned to property denied
     */
    public void setDenied(boolean denied) {
        this.denied = denied;
    }

}
