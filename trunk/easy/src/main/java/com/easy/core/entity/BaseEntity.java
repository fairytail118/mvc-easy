/**
 * cxybj.com Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.entity;

import java.util.Date;

/**
 * 基础的实体对象
 * 
 * @author wy
 * @version v 0.1 2013-6-16 下午3:24:06 wy Exp $
 */
public class BaseEntity extends Criteria {

    /** serialVersionUID */
    private static final long serialVersionUID = -1567359668784563422L;

    /** 创建人 */
    protected String          createUser;

    /** 创建时间 */
    protected Date            createTime;

    /** 修改人 */
    protected String          modifyUser;

    /** 修改时间 */
    protected Date            modifyTime;

    /**
     * Getter method for property <tt>createUser</tt>.
     * 
     * @return property value of createUser
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * Setter method for property <tt>createUser</tt>.
     * 
     * @param createUser
     *            value to be assigned to property createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * Getter method for property <tt>createTime</tt>.
     * 
     * @return property value of createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Setter method for property <tt>createTime</tt>.
     * 
     * @param createTime
     *            value to be assigned to property createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * Getter method for property <tt>modifyUser</tt>.
     * 
     * @return property value of modifyUser
     */
    public String getModifyUser() {
        return modifyUser;
    }

    /**
     * Setter method for property <tt>modifyUser</tt>.
     * 
     * @param modifyUser
     *            value to be assigned to property modifyUser
     */
    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    /**
     * Getter method for property <tt>modifyTime</tt>.
     * 
     * @return property value of modifyTime
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * Setter method for property <tt>modifyTime</tt>.
     * 
     * @param modifyTime
     *            value to be assigned to property modifyTime
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

}
