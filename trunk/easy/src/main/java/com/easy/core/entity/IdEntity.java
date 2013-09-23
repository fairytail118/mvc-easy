/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.entity;

/**
 * 有主键的实体
 * 
 * @author wy
 * @version v 0.1 2013-9-19 上午11:05:12 wy Exp $
 */
public class IdEntity extends BaseEntity {

    /** serialVersionUID */
    private static final long serialVersionUID = -7069551266081403764L;

    /** 主键ID */
    protected Long            id;

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
        IdEntity other = (IdEntity) obj;
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
