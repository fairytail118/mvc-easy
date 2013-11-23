/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.weixin.in.message;

import com.easy.weixin.message.enums.MsgType;

/**
 * 接收的文本消息
 * 
 * @author wy
 * @version v 0.1 2013-11-20 下午9:41:32 wy Exp $
 */
public class InTextMessage extends InMessage {

    /** serialVersionUID */
    private static final long serialVersionUID = -7481114279680474974L;

    /** 回复的消息内容 */
    private String content;

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
     * @see com.easy.weixin.in.message.InMessage#getMsgType()
     */
    @Override
    public MsgType getMsgType() {
        return MsgType.text;
    }

}
