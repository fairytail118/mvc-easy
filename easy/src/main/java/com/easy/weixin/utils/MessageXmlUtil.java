/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.weixin.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

/**
 * 微信中的xml解析
 * 
 * @author wy
 * @version v 0.1 2013-11-20 下午10:46:18 wy Exp $
 */
public class MessageXmlUtil {

    /** 日志 */
    private final static Logger log = LoggerFactory.getLogger(MessageXmlUtil.class);

    /**
     * 由于微信的消息节点名称都是唯一但存在子节点情况，故采用节点名称-对应的节点文本内容/或者子节点map
     * 
     * @param xml
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> parseInMessage(String messageXml) {

        try {

            StringReader sr = new StringReader(messageXml);
            InputSource is = new InputSource(sr);
            SAXBuilder sax = new SAXBuilder();
            Document doc = sax.build(is);
            //获得文件的根元素  
            Element root = doc.getRootElement();
            return (Map<String, Object>) parseElement(root);
        }

        catch (IOException e) {
            log.error("解析xml文件流异常", e);
        }
        catch (Exception e) {
            log.error("解析xml异常", e);
        }
        return null;
    }

    /**
     * 获取子节点的信息
     * 
     * @param node
     * @return
     */
    private static Object parseElement(Element element) {

        Map<String, Object> tagMap = new HashMap<String, Object>();
        //获得根元素的第一级子节点  
        List<Element> list = element.getChildren();
        if (list.isEmpty()) {
            return element.getTextTrim();
        }
        for (Element e : list) {
            tagMap.put(e.getName(), parseElement(e));
        }
        return tagMap;
    }

}
