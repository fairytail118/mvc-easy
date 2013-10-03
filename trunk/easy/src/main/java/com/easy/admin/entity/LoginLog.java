/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.entity;

import com.easy.core.entity.IdEntity;
import java.util.Date;

/**
 * 登陆日志
 * 
 * @author wy
 * @version v 0.1 13-09-28 上午 00:49:46 wy Exp $
 */
public class LoginLog extends IdEntity {

    /** serialVersionUID */
    private static final long serialVersionUID = 601863200966197328L;

    /** 用户名 */
    private String            username;

    /** 密码 */
    private String            password;

    /** 动作(登录/退出) */
    private String            action;

    /** 状态 */
    private String            status;

    /** 日志内容 */
    private String            content;

    /** 客户端IP */
    private String            clientIp;

    /** 客户端浏览器 */
    private String            clientBrowser;

    /** 登录时间 */
    private Date              loginTime;

    /** 客户端所在地区 */
    private String            clientArea;

    /** 客户端操作系统 */
    private String            clientOs;

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
     * Getter method for property <tt>action</tt>.
     * 
     * @return property value of action
     */
    public String getAction() {
        return action;
    }

    /**
     * Setter method for property <tt>action</tt>.
     * 
     * @param action value to be assigned to property action
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Getter method for property <tt>status</tt>.
     * 
     * @return property value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter method for property <tt>status</tt>.
     * 
     * @param status value to be assigned to property status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getter method for property <tt>content</tt>.
     * 
     * @return property value of content
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter method for property <tt>content</tt>.
     * 
     * @param content value to be assigned to property content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Getter method for property <tt>clientIp</tt>.
     * 
     * @return property value of clientIp
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * Setter method for property <tt>clientIp</tt>.
     * 
     * @param clientIp value to be assigned to property clientIp
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * Getter method for property <tt>clientBrowser</tt>.
     * 
     * @return property value of clientBrowser
     */
    public String getClientBrowser() {
        return clientBrowser;
    }

    /**
     * Setter method for property <tt>clientBrowser</tt>.
     * 
     * @param clientBrowser value to be assigned to property clientBrowser
     */
    public void setClientBrowser(String clientBrowser) {
        this.clientBrowser = clientBrowser;
    }

    /**
     * Getter method for property <tt>loginTime</tt>.
     * 
     * @return property value of loginTime
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * Setter method for property <tt>loginTime</tt>.
     * 
     * @param loginTime value to be assigned to property loginTime
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * Getter method for property <tt>clientArea</tt>.
     * 
     * @return property value of clientArea
     */
    public String getClientArea() {
        return clientArea;
    }

    /**
     * Setter method for property <tt>clientArea</tt>.
     * 
     * @param clientArea value to be assigned to property clientArea
     */
    public void setClientArea(String clientArea) {
        this.clientArea = clientArea;
    }

    /**
     * Getter method for property <tt>clientOs</tt>.
     * 
     * @return property value of clientOs
     */
    public String getClientOs() {
        return clientOs;
    }

    /**
     * Setter method for property <tt>clientOs</tt>.
     * 
     * @param clientOs value to be assigned to property clientOs
     */
    public void setClientOs(String clientOs) {
        this.clientOs = clientOs;
    }

}
