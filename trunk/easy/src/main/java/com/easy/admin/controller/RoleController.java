/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easy.admin.entity.Role;
import com.easy.admin.entity.RolePermission;
import com.easy.admin.service.PermissionService;
import com.easy.admin.service.RoleService;
import com.easy.core.common.Page;
import com.easy.core.controller.BaseController;
import com.easy.core.mvc.result.Result;
import com.easy.core.utils.RequestUtil;
import com.easy.core.validator.annotations.RequiredStringValidator;
import com.easy.core.validator.annotations.Validations;

/**
 * 角色
 * 
 * @author wy
 * @version v 0.1 2013-6-16 下午10:26:54 wy Exp $
 */
@Controller
@RequestMapping("/admin")
public class RoleController extends BaseController {

    /** 角色service */
    @Autowired
    private RoleService       roleService;

    /** 权限 */
    @Autowired
    private PermissionService permissionService;

    /**
     * 进入编辑页面
     * 
     * @param request
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/role_input")
    public String input(HttpServletRequest request, ModelMap model,
                        @RequestParam(value = "id", required = false) Long id) {

        if (id != null) {
            Role role = roleService.getByPrimaryKey(id);
            model.put("role", role);
            if (role != null && role.getCriteria().get("grantList") != null) {
                List<RolePermission> permissionList = role.get("grantList");
                List<Long> grantList = new ArrayList<Long>(permissionList.size());
                for (RolePermission rp : permissionList) {
                    grantList.add(rp.getPermissionId());
                }
                model.put("grantList", grantList);
            }
        } else {
            model.put("role", new Role());
        }

        model.put("gradeList", permissionService.gradeList());

        return "admin/role_input";
    }

    /**
     * 检查编码是否存在
     * 
     * @param request
     * @param code 编码
     * @param id id
     * @return
     */
    @RequestMapping(value = "/role_check_code")
    @ResponseBody
    public Result checkCode(HttpServletRequest request,
                            @RequestParam(value = "code", required = false) String code,
                            @RequestParam(value = "id", required = false) Long id) {

        if (StringUtils.isBlank(code)) {
            return new Result(false, "编码不能为空!");
        }

        try {
            boolean exists = roleService.checkCodeExists(code, id);
            if (!exists) {
                return new Result();
            }
            return new Result(false, "重复编码!");
        }
        catch (Exception e) {
            log.error("检查编码是否存在出错", e);
            return new Result(e);
        }
    }

    /**
     * 进入保存
     * 
     * @param request
     * @param model
     * @param role
     * @param grantList 分配的权限
     * @return
     */
    @Validations(

    requiredStringValidators = {
            @RequiredStringValidator(field = "name", message = "角色名称不能为空!", trim = true),
            @RequiredStringValidator(field = "code", message = "角色编码不能为空!", trim = true) })
    @RequestMapping(value = "/role_save")
    public String save(HttpServletRequest request, ModelMap model, Role role,
                       @RequestParam(value = "permissionId", required = false) List<Long> grantList) {
        if (RequestUtil.hasErrors(request)) {
            return "admin/role_input";
        }

        Role entity = new Role();
        //拷贝，去掉重复的
        BeanUtils.copyProperties(role, entity, new String[] { "createUser", "createTime",
                "modifyUser", "modifyTime" });

        roleService.save(role, grantList);

        return "redirect:role_list";
    }

    /**
     * 进入列表
     * 
     * @param request
     * @param model
     * @param page
     * @param role
     * @return
     */
    @RequestMapping(value = "/role_list")
    public String list(HttpServletRequest request, ModelMap model,
                       @RequestParam(value = "page", defaultValue = "1") int currentPage, Role role) {

        Page<Role> page = new Page<Role>();
        page.setCurrentPage(currentPage);
        // 设置查询条件
        page.setCriteria(role);
        roleService.page(page);
        model.put("page", page);
        return "admin/role_list";
    }

    /**
     * 批量删除
     * 
     * @param request
     * @param model
     * @param key
     * @return
     */
    @RequestMapping(value = "/role_delete")
    @ResponseBody
    public Result delete(HttpServletRequest request, ModelMap model, Long[] key) {
        try {
            roleService.deleteByPrimaryKeys(key);
            return new Result();
        }
        catch (Exception e) {
            log.error("role删除失败", e);
            return new Result(e);
        }
    }
}
