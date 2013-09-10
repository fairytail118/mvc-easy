/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.mvc;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author wy
 * @version v 0.1 2013-6-17 下午8:24:16 wy Exp $
 */
public class CustomWebBinding implements WebBindingInitializer {

    /**
     * @see org.springframework.web.bind.support.WebBindingInitializer#initBinder(org.springframework.web.bind.WebDataBinder,
     *      org.springframework.web.context.request.WebRequest)
     */
    @Override
    public void initBinder(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(Date.class, new DateEditor());
    }

}
