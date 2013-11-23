/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.weixin.out.message.handler;

import com.easy.weixin.out.message.OutMessage;

/**
 * 消息回复处理
 * 
 * @author wy
 * @version v 0.1 2013-11-21 下午11:07:25 wy Exp $
 */
public interface OutMessageHandler {

    /**
     * 处理回复消息
     * 
     * @param outMessage
     * @return
     */
    String handlerMessage(OutMessage outMessage);

}
