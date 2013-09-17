/**
 * cxybj.com Copyright (c) 2012-2013 All Rights Reserved.
 */
package codeworker.db.model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 表
 * 
 * @author wy
 * @version v 0.1 2013-6-1 上午9:54:04 wy Exp $
 */
public class Table {

    /** 表名 */
    private String                        name;

    /** 表说明 */
    private String                        desc;

    /** 主键类型 */
    private PrimaryType                   primaryType = PrimaryType.ONE;

    /**
     * 表字段
     */
    private List<Column>                  columnList;

    /** 主键列表 */
    private List<String>                  primaryList;

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
     * Getter method for property <tt>desc</tt>.
     * 
     * @return property value of desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Setter method for property <tt>desc</tt>.
     * 
     * @param desc
     *            value to be assigned to property desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Getter method for property <tt>primaryType</tt>.
     * 
     * @return property value of primaryType
     */
    public PrimaryType getPrimaryType() {
        return primaryType;
    }

    /**
     * Setter method for property <tt>primaryType</tt>.
     * 
     * @param primaryType
     *            value to be assigned to property primaryType
     */
    public void setPrimaryType(PrimaryType primaryType) {
        this.primaryType = primaryType;
    }

    /**
     * Getter method for property <tt>columnList</tt>.
     * 
     * @return property value of columnList
     */
    public List<Column> getColumnList() {
        Collections.sort(columnList);
        return columnList;
    }

    /**
     * Setter method for property <tt>columnList</tt>.
     * 
     * @param columnList
     *            value to be assigned to property columnList
     */
    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    /**
     * Getter method for property <tt>primaryList</tt>.
     * 
     * @return property value of primaryList
     */
    public List<String> getPrimaryList() {
        return primaryList;
    }

    /**
     * Setter method for property <tt>primaryList</tt>.
     * 
     * @param primaryList
     *            value to be assigned to property primaryList
     */
    public void setPrimaryList(List<String> primaryList) {
        this.primaryList = primaryList;
    }


}
