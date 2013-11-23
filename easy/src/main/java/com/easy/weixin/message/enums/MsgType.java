/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.weixin.message.enums;

/**
 * 消息类型
 * 
 * @author wy
 * @version v 0.1 2013-11-21 下午10:29:30 wy Exp $
 */
public enum MsgType {

    text("text", "文本"),

    image("image", "图片"),

    voice("voice", "语音"),

    video("video", "视频"),

    location("location", "地理位置"),

    link("link", "链接");

    /** 类型 */
    private String type;

    /** 类型名称 */
    private String name;

    /**
     * @param type
     * @param name
     */
    private MsgType(String type, String name) {
        this.type = type;
        this.name = name;
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
     * Getter method for property <tt>name</tt>.
     * 
     * @return property value of name
     */
    public String getName() {
        return name;
    }

}
