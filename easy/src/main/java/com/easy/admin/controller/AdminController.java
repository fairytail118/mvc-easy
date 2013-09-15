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
import com.easy.core.common.Page;
import com.easy.core.controller.BaseController;
import com.easy.core.mvc.result.Result;
import com.easy.core.utils.RequestUtils;
import com.easy.core.validator.annotations.RequiredStringValidator;
import com.easy.core.validator.annotations.StringLengthValidator;
import com.easy.core.validator.annotations.Validations;

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
	 * 进入编辑页面
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/admin_input")
	public String input(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "id", required = false) Long id) {

		if (id != null) {
			Admin admin = adminService.getByPrimaryKey(id);
			model.put("admin", admin);
		}

		return "admin/input";
	}

	/**
	 * 进入保存
	 * 
	 * @param request
	 * @param model
	 * @param admin
	 * @return
	 */
	@Validations(requiredStringValidators = { 
			@RequiredStringValidator(field = "name", key = "admin.name", trim = true) }, 
			stringLengthValidators = { @StringLengthValidator(field = "name", key = "admin.name.length", trim = true, maxLength = "12", minLength = "6") }

	)
	@RequestMapping(value = "/admin_save")
	public String save(HttpServletRequest request, ModelMap model, Admin admin) {

		if (RequestUtils.hasErrors(request)) {
			return "admin/input";
		}

		adminService.save(admin);

		return "redirect:admin_list";
	}

	/**
	 * 进入列表
	 * 
	 * @param request
	 * @param model
	 * @param page
	 * @param admin
	 * @return
	 */
	@RequestMapping(value = "/admin_list")
	public String list(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "page", defaultValue = "1") int currentPage,
			Admin admin) {

		Page<Admin> page = new Page<Admin>();
		page.setCurrentPage(currentPage);
		// 设置查询条件
		page.setCriteria(admin);
		adminService.page(page);
		model.put("page", page);
		return "admin/list";
	}

	/**
	 * 批量删除
	 * 
	 * @param request
	 * @param model
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "/admin_delete")
	@ResponseBody
	public Result delete(HttpServletRequest request, ModelMap model, Long[] key) {
		try {
			adminService.deleteByPrimaryKeys(key);
			return new Result();
		} catch (Exception e) {
			log.error("admin删除失败", e);
			return new Result(e);
		}
	}
}
