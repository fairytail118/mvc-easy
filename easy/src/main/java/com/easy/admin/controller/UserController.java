/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easy.admin.entity.User;
import com.easy.admin.service.UserService;
import com.easy.core.common.Page;
import com.easy.core.controller.BaseController;
import com.easy.core.mvc.result.Result;
import com.easy.core.utils.RequestUtil;
import com.easy.core.validator.annotations.RequiredStringValidator;
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
			System.out.println(user.getIsLocked());
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
						@RequiredStringValidator(field = "password", message = "密码不能为空!", trim = true),
						@RequiredStringValidator(field = "isLocked", message = "是否锁定不能为空!", trim = true),
						@RequiredStringValidator(field = "isEnabled", message = "是否启用不能为空!", trim = true),
						@RequiredStringValidator(field = "userType", message = "用户类型不能为空!", trim = true)		
			}
	)
	@RequestMapping(value = "/user_save")
	public String save(HttpServletRequest request, ModelMap model, User user) {
		if (RequestUtil.hasErrors(request)) {
			return "admin/user_input";
		}

		User entity = new User();
		//拷贝，去掉重复的
		BeanUtils.copyProperties(user, entity, new String[] { "createUser",
				"createTime", "modifyUser", "modifyTime" });

		userService.save(user);

		return "redirect:user_list";
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
			@RequestParam(value = "page", defaultValue = "1") int currentPage,
			User user) {

		Page<User> page = new Page<User>();
		page.setCurrentPage(currentPage);
		// 设置查询条件
		page.setCriteria(user);
		userService.page(page);
		model.put("page", page);
		return "admin/user_list";
	}

	/**
	 * 批量删除
	 * 
	 * @param request
	 * @param model
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "/user_delete")
	@ResponseBody
	public Result delete(HttpServletRequest request, ModelMap model, Long[] key) {
		try {
			userService.deleteByPrimaryKeys(key);
			return new Result();
		} catch (Exception e) {
			log.error("user删除失败", e);
			return new Result(e);
		}
	}
}