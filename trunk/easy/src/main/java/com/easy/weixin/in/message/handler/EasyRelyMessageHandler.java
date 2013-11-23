/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.weixin.in.message.handler;

import com.easy.weixin.in.message.InMessage;
import com.easy.weixin.in.message.InTextMessage;
import com.easy.weixin.out.message.OutMessage;

/**
 * 
 * @author wy
 * @version v 0.1 2013-11-21 下午11:05:31 wy Exp $
 */
public class EasyRelyMessageHandler implements ReplyMessageHandler {

    /** 
     * @see com.easy.weixin.in.message.handler.ReplyMessageHandler#replyMessage(com.easy.weixin.in.message.InMessage)
     */
    @Override
    public OutMessage replyMessage(InMessage inMessage) {
        if (inMessage == null) {
            return null;
        }

        if (inMessage instanceof InTextMessage) {

        }

        return null;
    }

}
