/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.easy.core.controller.BaseController;

/**
 * 首页
 * 
 * @author wy
 * @version v 0.1 2013-9-15 下午12:53:19 wy Exp $
 */
@Controller
@RequestMapping("/admin")
public class IndexController extends BaseController {

    /**
     * 首页
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request, ModelMap model) {

        return "admin/index";
    }
    
    

}
