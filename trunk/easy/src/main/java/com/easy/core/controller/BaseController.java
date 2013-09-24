/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.easy.core.mvc.DateEditor;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-10 上午7:39:41 wy Exp $
 */
public class BaseController {

    /** 日志 */
    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 注册转换器
     * 
     * @param paramWebDataBinder
     */
    @InitBinder
    protected final void initBinderCustomEditor(WebDataBinder paramWebDataBinder) {
        //去掉空格
        paramWebDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
        //日期转换
        paramWebDataBinder.registerCustomEditor(Date.class, new DateEditor());
    }

}
