/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.weixin.in.message.handler;

import com.easy.weixin.in.message.InMessage;
import com.easy.weixin.out.message.OutMessage;

/**
 * 
 * 回复消息
 * 
 * @author wy
 * @version v 0.1 2013-11-21 下午11:04:23 wy Exp $
 */
public interface ReplyMessageHandler {
    /**
     * 回复的消息
     * 
     * @param inMessage
     * @return
     */
    OutMessage replyMessage(InMessage inMessage);
}
