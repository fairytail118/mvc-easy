/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.weixin.in.message.handler;

import java.util.Map;

import com.easy.weixin.in.message.InMessage;

/**
 * 接收消息处理
 * 
 * @author wy
 * @version v 0.1 2013-11-20 下午9:37:37 wy Exp $
 */
public interface InMessageHandler<T extends InMessage> {

    /**
     * 处理消息
     * 
     * @param tagMap
     * @return
     */
    T handlerMessage(Map<String, Object> tagMap);

    /**
     * 是否支持该消息
     * 
     * @param tagMap
     * @return
     */
    boolean support(Map<String, Object> tagMap);

}
