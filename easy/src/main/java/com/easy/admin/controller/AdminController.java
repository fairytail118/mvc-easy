/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easy.admin.entity.Admin;
import com.easy.admin.service.AdminService;
import com.easy.common.Page;
import com.easy.controller.BaseController;
import com.easy.core.mvc.result.Result;

/**
 * 
 * @author wy
 * @version v 0.1 2013-6-16 下午10:26:54 wy Exp $
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    /** 管理员service */
    @Autowired
    private AdminService adminService;

    /**
     * 进入编辑或者新增
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin_input")
    public String input(@RequestParam(value = "id", required = false) Long id, ModelMap model) {

        if (id != null) {
            Admin admin = adminService.getByPrimaryKey(id);
            model.put("admin", admin);
        }

        return "admin/input";
    }

    /**
     * 进入保存
     * 
     * @param admin
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin_save")
    public String save(Admin admin, HttpServletRequest request, ModelMap model) {
        
        adminService.save(admin);

        return "redirect:admin_list";
    }

    /**
     * 进入列表
     * 
     * @param page
     * @param admin
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin_list")
    public String list(Page<Admin> page, Admin admin, HttpServletRequest request, ModelMap model) {
        
        //设置查询条件
        page.setCriteria(admin);
        
        adminService.page(page);
        
        return "admin/list";
    }

    /**
     * 批量删除
     * 
     * @param key
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result delete(Long[] key, HttpServletRequest request, ModelMap model) {
        try {
            adminService.deleteByPrimaryKeys(key);
            return new Result();
        }
        catch (Exception e) {
            log.error("admin删除失败", e);
            return new Result(e.getMessage());
        }
    }
}
