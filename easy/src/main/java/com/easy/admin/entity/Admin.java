/**
 * cxybj.com Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.entity;

import com.easy.core.entity.IdEntity;

/**
 * 管理员
 * 
 * @author wy
 * @version v 0.1 2013-6-16 下午3:28:56 wy Exp $
 */
public class Admin extends IdEntity {

    /** serialVersionUID */
    private static final long serialVersionUID = -7241410078601395875L;

    /** 姓名 */
    private String            name;

    /** 邮箱 */
    private String            email;

    /** 登录名 */
    private String            loginName;

    /** 密码 */
    private String            password;

    /** 是否锁定 */
    private Boolean           isLocked;

    /** 是否启用 */
    private Boolean           isEnabled;

    /** 是否超级管理员 */
    private Boolean           isSuper;

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
     * Getter method for property <tt>email</tt>.
     * 
     * @return property value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter method for property <tt>email</tt>.
     * 
     * @param email
     *            value to be assigned to property email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method for property <tt>loginName</tt>.
     * 
     * @return property value of loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * Setter method for property <tt>loginName</tt>.
     * 
     * @param loginName
     *            value to be assigned to property loginName
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * Getter method for property <tt>password</tt>.
     * 
     * @return property value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter method for property <tt>password</tt>.
     * 
     * @param password
     *            value to be assigned to property password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter method for property <tt>isLocked</tt>.
     * 
     * @return property value of isLocked
     */
    public Boolean getIsLocked() {
        return isLocked;
    }

    /**
     * Setter method for property <tt>isLocked</tt>.
     * 
     * @param isLocked
     *            value to be assigned to property isLocked
     */
    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * Getter method for property <tt>isEnabled</tt>.
     * 
     * @return property value of isEnabled
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     * Setter method for property <tt>isEnabled</tt>.
     * 
     * @param isEnabled
     *            value to be assigned to property isEnabled
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * Getter method for property <tt>isSuper</tt>.
     * 
     * @return property value of isSuper
     */
    public Boolean getIsSuper() {
        return isSuper;
    }

    /**
     * Setter method for property <tt>isSuper</tt>.
     * 
     * @param isSuper
     *            value to be assigned to property isSuper
     */
    public void setIsSuper(Boolean isSuper) {
        this.isSuper = isSuper;
    }

}
