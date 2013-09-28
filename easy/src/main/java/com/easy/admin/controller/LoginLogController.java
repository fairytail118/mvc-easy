/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.easy.admin.entity.LoginLog;
import com.easy.admin.service.LoginLogService;
import com.easy.core.common.Page;
import com.easy.core.controller.BaseController;
import com.easy.core.security.util.SecurityUtil;

/**
 * 登陆日志
 * 
 * @author wy
 * @version v 0.1 2013-6-16 下午10:26:54 wy Exp $
 */
@Controller
@RequestMapping("/admin")
public class LoginLogController extends BaseController {

    /** 登陆日志service */
    @Autowired
    private LoginLogService loginLogService;

    /**
     * 进入列表
     * 
     * @param request
     * @param model
     * @param page
     * @param loginLog
     * @return
     */
    @RequestMapping(value = "/login_log_list")
    public String list(HttpServletRequest request, ModelMap model,
                       @RequestParam(value = "page", defaultValue = "1") int currentPage,
                       LoginLog loginLog) {

        Page<LoginLog> page = new Page<LoginLog>();
        page.setCurrentPage(currentPage);
        // 设置查询条件
        page.setCriteria(loginLog);
        loginLogService.page(page);
        model.put("page", page);
        return "admin/login_log_list";
    }

    /**
     * 我的登录情况
     * 
     * @param request
     * @param model
     * @param currentPage
     * @param startDate
     * @param endDate
     * @param loginLog
     * @return
     */
    @RequestMapping(value = "/login_log_my_list")
    public String myList(HttpServletRequest request, ModelMap model,
                         @RequestParam(value = "page", defaultValue = "1") int currentPage,
                         @RequestParam(value = "startDate", required = false) Date startDate,
                         @RequestParam(value = "endDate", required = false) Date endDate,
                         LoginLog loginLog) {

        Page<LoginLog> page = new Page<LoginLog>();
        page.setCurrentPage(currentPage);
        //查询的用户
        loginLog.setUsername(SecurityUtil.getLoginUser().getUsername());

        //日期区间
        loginLog.put("startDate", startDate);
        loginLog.put("endDate", endDate);
        // 设置查询条件
        page.setCriteria(loginLog);
        loginLogService.page(page);
        model.put("page", page);
        return "admin/login_log_my_list";
    }

}
