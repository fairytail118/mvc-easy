/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.weixin.in.message.handler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easy.weixin.exceptions.WeixinException;
import com.easy.weixin.in.message.InMessage;
import com.easy.weixin.message.enums.MsgType;

/**
 * 
 * @author wy
 * @version v 0.1 2013-11-20 下午10:00:30 wy Exp $
 */
public abstract class AbstractInMessageHandler<T extends InMessage> implements InMessageHandler<T> {

    /** 日志 */
    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * @see com.easy.weixin.in.message.handler.InMessageHandler#support(java.util.Map)
     */
    @Override
    public boolean support(Map<String, Object> tagMap) {
        return getSupportMsgType().equals(getMsgType(tagMap));
    }

    /**
     * 设置公共的消息部分
     * 
     * @param message
     * @param tagMap
     */
    protected void initMessage(InMessage message, Map<String, Object> tagMap) {

        if (!this.support(tagMap) || message.getMsgType() != getSupportMsgType()) {
            throw new WeixinException("该处理器不支持Message中类型为" + message.getMsgType() + "的消息");
        }

        message.setToUserName((String) tagMap.get("ToUserName"));
        message.setFromUserName((String) tagMap.get("FromUserName"));
        message.setCreateTime((String) tagMap.get("CreateTime"));
    }

    /**
     * 获取消息类型
     * 
     * @param tagMap
     * @return
     */
    protected String getMsgType(Map<String, Object> tagMap) {
        return (String) tagMap.get("MsgType");
    }

    /**
     * 获取支持的消息类型
     * 
     * @return
     */
    protected abstract MsgType getSupportMsgType();

}
