/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.easy.admin.entity.User;
import com.easy.core.controller.BaseController;

/**
 * 首页
 * 
 * @author wy
 * @version v 0.1 2013-9-15 下午12:53:19 wy Exp $
 */
public class IndexController extends BaseController {

    @InitBinder("user")
    public void initBinder(WebDataBinder binder) {
        // 别名前缀
        binder.setFieldDefaultPrefix("user.");
    }

    public String index(ModelMap model, @ModelAttribute("user") User user) {

        return null;
    }

}