/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.entity;

import java.util.Collection;

import org.apache.commons.lang.BooleanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.easy.core.entity.IdEntity;

/**
 * 用户
 * 
 * @author wy
 * @version v 0.1 13-09-23 下午 20:32:31 wy Exp $
 */
public class User extends IdEntity implements UserDetails {

    /** serialVersionUID */
    private static final long            serialVersionUID = 686443103191517444L;

    /** 姓名 */
    private String                       name;

    /** 邮箱 */
    private String                       email;

    /** 手机 */
    private String                       mobile;

    /** 用户名 */
    private String                       username;

    /** 密码 */
    private String                       password;

    /** 是否锁定 */
    private Boolean                      isLocked;

    /** 是否启用 */
    private Boolean                      isEnabled;

    /** 用户类型 */
    private String                       userType;

    /** 所具有的权限 */
    private Collection<GrantedAuthority> authorities;

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
     * @param name value to be assigned to property name
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
     * @param email value to be assigned to property email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method for property <tt>mobile</tt>.
     * 
     * @return property value of mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Setter method for property <tt>mobile</tt>.
     * 
     * @param mobile value to be assigned to property mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * Getter method for property <tt>username</tt>.
     * 
     * @return property value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter method for property <tt>username</tt>.
     * 
     * @param username value to be assigned to property username
     */
    public void setUsername(String username) {
        this.username = username;
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
     * @param password value to be assigned to property password
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
     * @param isLocked value to be assigned to property isLocked
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
     * @param isEnabled value to be assigned to property isEnabled
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * Getter method for property <tt>userType</tt>.
     * 
     * @return property value of userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Setter method for property <tt>userType</tt>.
     * 
     * @param userType value to be assigned to property userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Setter method for property <tt>authorities</tt>.
     * 
     * @param authorities value to be assigned to property authorities
     */
    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    /**
     * 账号没有过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号没有锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return BooleanUtils.isFalse(isLocked);
    }

    /**
     * 凭证没有过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return BooleanUtils.isTrue(this.getIsEnabled());
    }

}
