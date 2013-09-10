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

    /** 主键ID */
    protected Long            id;

    /** 创建人 */
    protected String          createUser;

    /** 创建时间 */
    protected Date            createTime;

    /** 修改人 */
    protected String          modifyUser;

    /** 修改时间 */
    protected Date            modifyTime;

    /**
     * Getter method for property <tt>id</tt>.
     * 
     * @return property value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     * 
     * @param id
     *            value to be assigned to property id
     */
    public void setId(Long id) {
        this.id = id;
    }

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

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        BaseEntity other = (BaseEntity) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.getClass().getName() + "[id=" + id + "]";
    }
}
