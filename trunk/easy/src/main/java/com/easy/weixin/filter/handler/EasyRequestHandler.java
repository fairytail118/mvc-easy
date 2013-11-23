/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.weixin.filter.handler;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.easy.core.utils.ResponseUtil;
import com.easy.weixin.in.message.InMessage;
import com.easy.weixin.in.message.handler.InMessageHandler;
import com.easy.weixin.in.message.handler.ReplyMessageHandler;
import com.easy.weixin.out.message.OutMessage;
import com.easy.weixin.out.message.handler.OutMessageHandler;
import com.easy.weixin.token.RequestToken;
import com.easy.weixin.utils.MessageXmlUtil;

/**
 * 消息处理
 * 
 * @author wy
 * @version v 0.1 2013-11-20 下午9:27:55 wy Exp $
 */
public class EasyRequestHandler extends AbstractRequestHandler implements InitializingBean {

    /** 不同消息类型的处理器 */
    private List<InMessageHandler<? extends InMessage>> registerMessageHandlerList;

    /** 消息回复 */
    private ReplyMessageHandler replyMessageHandler;

    /** 消息回复处理 */
    private OutMessageHandler outMessageHandler;

    /**
     * @see com.easy.weixin.filter.handler.AbstractRequestHandler#handleMessageRequest(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain,
     *      com.easy.weixin.token.RequestToken)
     */
    @Override
    public void handleMessageRequest(HttpServletRequest request, HttpServletResponse response,
                                     FilterChain filterChain, RequestToken requestToken) {

        String message = getMessage(request);
        log.debug("接收的xml消息内容为:{}", message);

        Map<String, Object> tagMap = MessageXmlUtil.parseInMessage(message);

        InMessageHandler<? extends InMessage> messageHandler = null;

        for (InMessageHandler<? extends InMessage> handler : registerMessageHandlerList) {
            if (handler.support(tagMap)) {
                messageHandler = handler;
                break;
            }
        }
        //如果没有处理器支持该消息
        if (messageHandler == null) {
            return;
        }

        InMessage inMessage = messageHandler.handlerMessage(tagMap);

        //保存消息
        OutMessage outMessage = replyMessageHandler.replyMessage(inMessage);

        String out = outMessageHandler.handlerMessage(outMessage);
        if (out != null) {
            ResponseUtil.writeXml(response, out);
        }

    }

    /**
     * 获取xml消息
     * 
     * @param request
     * @return
     */
    protected String getMessage(HttpServletRequest request) {
        try {
            ServletInputStream is = request.getInputStream();
            return IOUtils.toString(is, "UTF-8");
        }
        catch (IOException e) {
            log.error("获取消息出现异常", e);
        }
        return "";
    }

    /**
     * 注册可以处理的消息类型
     * 
     * @param registerMessageHandlerList
     */
    public void setRegisterMessageHandlerList(List<InMessageHandler<? extends InMessage>> registerMessageHandlerList) {
        this.registerMessageHandlerList = registerMessageHandlerList;
    }

    /**
     * 回复消息处理器
     * 
     * @param replyMessageHandler
     */
    public void setReplyMessageHandler(ReplyMessageHandler replyMessageHandler) {
        this.replyMessageHandler = replyMessageHandler;
    }

    /**
     * 设置回复消息转换器
     * 
     * @param outMessageHandler
     */
    public void setOutMessageHandler(OutMessageHandler outMessageHandler) {
        this.outMessageHandler = outMessageHandler;
    }

    /**
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notEmpty(registerMessageHandlerList, "注册的消息处理器不能为空!");
        Assert.notNull(replyMessageHandler, "消息回复处理器不能为空");
        Assert.notNull(outMessageHandler, "消息回复转换处理器不能为空");
    }

}
