/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.common;

import java.io.Serializable;
import java.util.List;

/**
 * 分页
 * 
 * @author wy
 * @version v 0.1 2013-3-5 下午11:30:29 wy Exp $
 */
public class Page<T> implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID  = 1896622323272784569L;

    /** 默认显示条数 */
    private static final int  DEFAULT_PAGE_SIZE = 20;

    /** 数据对象 */
    private List<T>           list;

    /** 查询条件 */
    private T                 criteria;

    /** 当前页码 */
    private int               currentPage       = 1;

    /** 每页记录数 */
    private int               pageSize          = DEFAULT_PAGE_SIZE;

    /** 总记录数 */
    private int               totalCount        = 0;

    /**
     * 拷贝转换的分页
     * 
     * @return
     */
    public <E> Page<E> newPage() {
        Page<E> page = new Page<E>();
        page.currentPage = this.getCurrentPage();
        page.pageSize = this.getPageSize();
        page.totalCount = this.getTotalCount();
        return page;
    }

    /**
     * 获取总页数
     * 
     * @return
     */
    public Integer getPageCount() {
        int pageCount = totalCount / pageSize;
        if (totalCount % pageSize > 0) {
            pageCount++;
        }
        return pageCount;
    }

    /**
     * Getter method for property <tt>list</tt>.
     * 
     * @return property value of list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * Setter method for property <tt>list</tt>.
     * 
     * @param list
     *            value to be assigned to property list
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * Getter method for property <tt>currentPage</tt>.
     * 
     * @return property value of currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * Setter method for property <tt>currentPage</tt>.
     * 
     * @param currentPage
     *            value to be assigned to property currentPage
     */
    public void setCurrentPage(Integer currentPage) {
        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        this.currentPage = currentPage;
    }

    /**
     * 页码偏移量
     * 
     * @return
     */
    public int getOffset() {
        return (currentPage - 1) * pageSize;
    }

    /**
     * Getter method for property <tt>pageSize</tt>.
     * 
     * @return property value of pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Setter method for property <tt>pageSize</tt>.
     * 
     * @param pageSize
     *            value to be assigned to property pageSize
     */
    public void setPageSize(Integer pageSize) {
        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        this.pageSize = pageSize;
    }

    /**
     * Getter method for property <tt>totalCount</tt>.
     * 
     * @return property value of totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * Setter method for property <tt>totalCount</tt>.
     * 
     * @param totalCount
     *            value to be assigned to property totalCount
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * Getter method for property <tt>criteria</tt>.
     * 
     * @return property value of criteria
     */
    public T getCriteria() {
        return criteria;
    }

    /**
     * Setter method for property <tt>criteria</tt>.
     * 
     * @param criteria
     *            value to be assigned to property criteria
     */
    public void setCriteria(T criteria) {
        this.criteria = criteria;
    }

}
