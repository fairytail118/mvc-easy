/**
 * cxybj.com Copyright (c) 2012-2013 All Rights Reserved.
 */
package codeworker.db.model;

/**
 * 字段
 * 
 * @author wy
 * @version v 0.1 2013-6-1 上午9:54:15 wy Exp $
 */
public class Column implements Comparable<Column> {

    /** 字段名称 */
    private String   column;

    /** 对应的java类型 */
    private String typeClass;

    /** 是否主键 */
    private boolean  isPrimary = false;

    /** 是否外键 */
    private boolean  isForeign = false;

    /** 字段注释 */
    private String   comment;
    
    /**是否允许为空*/
    private boolean isNullable;


	/** 字段长度 */
    private int      length;

    /**
     * 是否自增
     */
    private boolean  autoincrement;
    
    /**字段的数据库类型*/
    private String columType;

   
	/**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Column o) {
        if (o == null) {
            return -1;
        }
        if (isPrimary && !o.isPrimary) {
            return 1;
        }
        return 0;
    }

    /**
     * Getter method for property <tt>column</tt>.
     * 
     * @return property value of column
     */
    public String getColumn() {
        return column;
    }

    /**
     * Setter method for property <tt>column</tt>.
     * 
     * @param column
     *            value to be assigned to property column
     */
    public void setColumn(String column) {
        this.column = column;
    }

   
    /**
     *  对应的java类型 
     * 
     * @return property value of typeClass
     */
    public String getTypeClass() {
        return typeClass;
    }

    /**
     *对应的java类型 
     * 
     * @param typeClass
     *            value to be assigned to property typeClass
     */
    public void setTypeClass(String typeClass) {
        this.typeClass = typeClass;
    }

    /**
     * Getter method for property <tt>isPrimary</tt>.
     * 
     * @return property value of isPrimary
     */
    public boolean isPrimary() {
        return isPrimary;
    }

    /**
     * Setter method for property <tt>isPrimary</tt>.
     * 
     * @param isPrimary
     *            value to be assigned to property isPrimary
     */
    public void setPrimary(boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    /**
     * Getter method for property <tt>comment</tt>.
     * 
     * @return property value of comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Setter method for property <tt>comment</tt>.
     * 
     * @param comment
     *            value to be assigned to property comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Getter method for property <tt>length</tt>.
     * 
     * @return property value of length
     */
    public int getLength() {
        return length;
    }

    /**
     * Setter method for property <tt>length</tt>.
     * 
     * @param length
     *            value to be assigned to property length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Getter method for property <tt>autoincrement</tt>.
     * 
     * @return property value of autoincrement
     */
    public boolean isAutoincrement() {
        return autoincrement;
    }

    /**
     * Setter method for property <tt>autoincrement</tt>.
     * 
     * @param autoincrement
     *            value to be assigned to property autoincrement
     */
    public void setAutoincrement(boolean autoincrement) {
        this.autoincrement = autoincrement;
    }

    /**
     * Getter method for property <tt>isForeign</tt>.
     * 
     * @return property value of isForeign
     */
    public boolean isForeign() {
        return isForeign;
    }

    /**
     * Setter method for property <tt>isForeign</tt>.
     * 
     * @param isForeign value to be assigned to property isForeign
     */
    public void setForeign(boolean isForeign) {
        this.isForeign = isForeign;
    }
    
    public boolean getIsNullable() {
		return isNullable;
	}

	public void setNullable(boolean isNullable) {
		this.isNullable = isNullable;
	}
	
	 /**字段的数据库类型*/
    public String getColumType() {
		return columType;
	}
    /**字段的数据库类型*/
	public void setColumType(String columType) {
		this.columType = columType;
	}


}
