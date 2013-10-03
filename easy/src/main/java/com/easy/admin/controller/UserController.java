/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easy.admin.entity.User;
import com.easy.admin.service.UserService;
import com.easy.core.common.Page;
import com.easy.core.controller.BaseController;
import com.easy.core.mvc.result.Result;
import com.easy.core.security.util.SecurityUtil;
import com.easy.core.utils.RequestUtil;
import com.easy.core.validator.annotations.EmailValidator;
import com.easy.core.validator.annotations.EqualsStringValidator;
import com.easy.core.validator.annotations.RequiredStringValidator;
import com.easy.core.validator.annotations.StringLengthValidator;
import com.easy.core.validator.annotations.Validations;

/**
 * 用户
 * 
 * @author wy
 * @version v 0.1 2013-6-16 下午10:26:54 wy Exp $
 */
@Controller
@RequestMapping("/admin")
public class UserController extends BaseController {

    /** 用户service */
    @Autowired
    private UserService userService;

    /**
     * 进入编辑页面
     * 
     * @param request
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/user_input")
    public String input(HttpServletRequest request, ModelMap model,
                        @RequestParam(value = "id", required = false) Long id) {

        if (id != null) {
            User user = userService.getByPrimaryKey(id);
            model.put("user", user);
        } else {
            //设置一个空的
            model.put("user", new User());
        }

        return "admin/user_input";
    }

    /**
     * 进入保存
     * 
     * @param request
     * @param model
     * @param user
     * @return
     */
    @Validations(

    requiredStringValidators = {
            @RequiredStringValidator(field = "name", message = "姓名不能为空!", trim = true),
            @RequiredStringValidator(field = "email", message = "邮箱不能为空!", trim = true),
            @RequiredStringValidator(field = "mobile", message = "手机不能为空!", trim = true),
            @RequiredStringValidator(field = "username", message = "用户名不能为空!", trim = true),
            @RequiredStringValidator(field = "userType", message = "用户类型不能为空!", trim = true) },

    stringLengthValidators = { @StringLengthValidator(field = "username", message = "用户名只能在6-12位之间", minLength = "6", maxLength = "12") },

    emailValidators = { @EmailValidator(field = "email", message = "邮箱格式不正确!") })
    @RequestMapping(value = "/user_save")
    public String save(HttpServletRequest request, ModelMap model,
                       @RequestParam(value = "rePassword") String rePassword, User user) {

        if (user.getId() == null && StringUtils.isBlank(user.getPassword())) {
            RequestUtil.addFormError(request, "password", "密码不能为空!");
        } else if (StringUtils.isNotBlank(user.getPassword())
                   && !StringUtils.equals(user.getPassword(), rePassword)) {
            RequestUtil.addFormError(request, "rePassword", "两次密码不一致!");
        }

        if (RequestUtil.hasErrors(request)) {
            return "admin/user_input";
        }

        User entity = new User();
        //拷贝，去掉重复的
        BeanUtils.copyProperties(user, entity, new String[] { "createUser", "createTime",
                "modifyUser", "modifyTime", "isLocked", "isEnabled" });

        userService.save(user);

        return "redirect:user_list";
    }

    /**
     * 检查用户名是否存在
     * 
     * @param request
     * @param code 编码
     * @param id id
     * @return
     */
    @RequestMapping(value = "/user_check_username")
    @ResponseBody
    public Result checkCode(HttpServletRequest request,
                            @RequestParam(value = "username", required = false) String username) {

        if (StringUtils.isBlank(username)) {
            return new Result(false, "用户名不能为空!");
        }

        try {
            boolean exists = userService.checkUsernameExists(username);
            if (!exists) {
                return new Result();
            }
            return new Result(false, "用户名重复!");
        }
        catch (Exception e) {
            log.error("检查用户名是否存在出错", e);
            return new Result(e);
        }
    }

    /**
     * 进入列表
     * 
     * @param request
     * @param model
     * @param page
     * @param user
     * @return
     */
    @RequestMapping(value = "/user_list")
    public String list(HttpServletRequest request, ModelMap model,
                       @RequestParam(value = "page", defaultValue = "1") int currentPage, User user) {

        Page<User> page = new Page<User>();
        page.setCurrentPage(currentPage);
        // 设置查询条件
        page.setCriteria(user);
        userService.page(page);
        model.put("page", page);
        return "admin/user_list";
    }

    /**
     * 修改密码
     * 
     * @param request
     * @param model
     * @param newPassword 新密码
     * @param rePassword 确认密码
     * @param password 原来密码
     * @return
     */
    @Validations(

    requiredStringValidators = {
            @RequiredStringValidator(field = "newPassword", message = "新密码不能为空!", trim = true),
            @RequiredStringValidator(field = "rePassword", message = "新密码不能为空!", trim = true),
            @RequiredStringValidator(field = "password", message = "原密码不能为空!", trim = true) },

    equalsStringValidators = { @EqualsStringValidator(field = "rePassword", otherField = "newPassword", message = "两次密码输入不一致", trim = true) })
    @RequestMapping(value = "/change_pwd", method = { RequestMethod.POST })
    @ResponseBody
    public Result changePassword(HttpServletRequest request, ModelMap model,
                                 @RequestParam(value = "newPassword") String newPassword,
                                 @RequestParam(value = "rePassword") String rePassword,
                                 @RequestParam(value = "password") String password) {
        if (RequestUtil.hasErrors(request)) {
            return new Result(false, RequestUtil.getFormErrors(request));
        }

        try {
            userService.updatePassowrd(SecurityUtil.getLoginUser().getUsername(), newPassword,
                password);
            return new Result();
        }
        catch (Exception e) {
            log.error("密码修改失败", e);
            return new Result(e);
        }

    }

    /**
     * 进入修改密码页面
     * 
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/change_pwd", method = { RequestMethod.GET })
    public String changePasswordInput(HttpServletRequest request, ModelMap model) {
        return "admin/change_pwd";
    }

    /**
     * 改变禁用启用状态
     * 
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/user_switch_enable")
    @ResponseBody
    public Result switchEnable(HttpServletRequest request,
                               @RequestParam(value = "id", required = false) Long id) {

        if (id == null) {
            return new Result(false, "id不能为空");
        }

        try {
            userService.switchEnable(id);
            return new Result();
        }
        catch (Exception e) {
            log.error("改变禁用启用状态失败", e);
            return new Result(e);
        }
    }

    /**
     * 改变锁定状态
     * 
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/user_switch_lock")
    @ResponseBody
    public Result switchLock(HttpServletRequest request,
                             @RequestParam(value = "id", required = false) Long id) {
        if (id == null) {
            return new Result(false, "id不能为空");
        }

        try {
            userService.switchLock(id);
            return new Result();
        }
        catch (Exception e) {
            log.error("改变锁定状态失败", e);
            return new Result(e);
        }
    }
}
