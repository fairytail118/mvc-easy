/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.weixin.message.enums;

import com.easy.weixin.in.message.InMessage;
import com.easy.weixin.in.message.handler.InMessageHandler;
import com.easy.weixin.in.message.handler.InTextMessageHandler;

/**
 * 
 * @author wy
 * @version v 0.1 2013-11-20 下午9:39:31 wy Exp $
 */
public enum InMessageType {

    text("text", InTextMessageHandler.class),

    ;

    /** 消息类型 */
    private String type;

    /** 消息处理者 */
    private Class<? extends InMessageHandler<? extends InMessage>> messageHandlerClass;

    /**
     * 
     * @param type
     * @param messageHandlerClass
     */
    InMessageType(String type,
                  Class<? extends InMessageHandler<? extends InMessage>> messageHandlerClass) {
        this.type = type;
        this.messageHandlerClass = messageHandlerClass;
    }

    public static Class<? extends InMessageHandler<? extends InMessage>> valueMessageType(String type) {
        
        return messageHandlerClass;
    }

    /**
     * Getter method for property <tt>type</tt>.
     * 
     * @return property value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter method for property <tt>type</tt>.
     * 
     * @param type value to be assigned to property type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter method for property <tt>messageClass</tt>.
     * 
     * @return property value of messageClass
     */
    public Class<? extends InMessage> getMessageClass() {
        return messageClass;
    }

    /**
     * Setter method for property <tt>messageClass</tt>.
     * 
     * @param messageClass value to be assigned to property messageClass
     */
    public void setMessageClass(Class<? extends InMessage> messageClass) {
        this.messageClass = messageClass;
    }

    /**
     * Getter method for property <tt>messageHandlerClass</tt>.
     * 
     * @return property value of messageHandlerClass
     */
    public Class<? extends InMessageHandler> getMessageHandlerClass() {
        return messageHandlerClass;
    }

    /**
     * Setter method for property <tt>messageHandlerClass</tt>.
     * 
     * @param messageHandlerClass value to be assigned to property
     *            messageHandlerClass
     */
    public void setMessageHandlerClass(Class<? extends InMessageHandler> messageHandlerClass) {
        this.messageHandlerClass = messageHandlerClass;
    }

}
