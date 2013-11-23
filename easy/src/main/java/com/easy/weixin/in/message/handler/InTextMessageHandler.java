/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.weixin.in.message.handler;

import java.util.Map;

import com.easy.weixin.in.message.InTextMessage;
import com.easy.weixin.message.enums.MsgType;

/**
 * 文本消息处理
 * 
 * @author wy
 * @version v 0.1 2013-11-20 下午9:42:10 wy Exp $
 */
public class InTextMessageHandler extends AbstractInMessageHandler<InTextMessage> {

    /**
     * @see com.easy.weixin.in.message.handler.InMessageHandler#handlerMessage(java.util.Map)
     */
    @Override
    public InTextMessage handlerMessage(Map<String, Object> xmlMap) {

        InTextMessage inTextMessage = new InTextMessage();

        initMessage(inTextMessage, xmlMap);

        inTextMessage.setContent((String) xmlMap.get("Content"));

        return inTextMessage;
    }

    /**
     * @see com.easy.weixin.in.message.handler.AbstractInMessageHandler#getSupportMsgType()
     */
    @Override
    protected MsgType getSupportMsgType() {
        return MsgType.text;
    }

}
