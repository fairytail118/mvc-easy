/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.weixin.in.message;

import java.io.Serializable;

import com.easy.weixin.message.enums.MsgType;

/**
 * 接收的消息
 * 
 * @author wy
 * @version v 0.1 2013-11-20 下午9:18:39 wy Exp $
 */
public abstract class InMessage implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -53481546182880763L;

    /** 接收方帐号（收到的OpenID） */
    protected String toUserName;

    /** 开发者微信号 */
    protected String fromUserName;

    /** 消息创建时间 （整型） */
    protected String createTime;

    /**
     * Getter method for property <tt>toUserName</tt>.
     * 
     * @return property value of toUserName
     */
    public String getToUserName() {
        return toUserName;
    }

    /**
     * Setter method for property <tt>toUserName</tt>.
     * 
     * @param toUserName value to be assigned to property toUserName
     */
    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    /**
     * Getter method for property <tt>fromUserName</tt>.
     * 
     * @return property value of fromUserName
     */
    public String getFromUserName() {
        return fromUserName;
    }

    /**
     * Setter method for property <tt>fromUserName</tt>.
     * 
     * @param fromUserName value to be assigned to property fromUserName
     */
    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    /**
     * Getter method for property <tt>createTime</tt>.
     * 
     * @return property value of createTime
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * Setter method for property <tt>createTime</tt>.
     * 
     * @param createTime value to be assigned to property createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取消息类型
     * 
     * @return
     */
    public abstract MsgType getMsgType();

}
