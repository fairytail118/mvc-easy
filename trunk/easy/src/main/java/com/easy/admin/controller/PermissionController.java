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

import com.easy.admin.entity.Permission;
import com.easy.admin.service.PermissionService;
import com.easy.core.common.Page;
import com.easy.core.controller.BaseController;
import com.easy.core.mvc.result.Result;
import com.easy.core.utils.RequestUtil;
import com.easy.core.validator.annotations.RequiredStringValidator;
import com.easy.core.validator.annotations.Validations;

/**
 * 权限
 * 
 * @author wy
 * @version v 0.1 2013-6-16 下午10:26:54 wy Exp $
 */
@Controller
@RequestMapping("/admin")
public class PermissionController extends BaseController {

	/** 权限service */
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
	@RequestMapping(value = "/permission_input")
	public String input(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "id", required = false) Long id) {

		if (id != null) {
			Permission permission = permissionService.getByPrimaryKey(id);
			model.put("permission", permission);
		}

		return "admin/permission_input";
	}

	/**
	 * 进入保存
	 * 
	 * @param request
	 * @param model
	 * @param permission
	 * @return
	 */
	@Validations(
	
		requiredStringValidators = { 
				@RequiredStringValidator(field = "parentId", message = "父权限ID不能为空!", trim = true),
										@RequiredStringValidator(field = "name", message = "权限名称不能为空!", trim = true),
						@RequiredStringValidator(field = "code", message = "权限编码不能为空!", trim = true)		
			}
	)
	@RequestMapping(value = "/permission_save")
	public String save(HttpServletRequest request, ModelMap model, Permission permission) {
		if (RequestUtil.hasErrors(request)) {
			return "admin/permission_input";
		}

		Permission entity = new Permission();
		//拷贝，去掉重复的
		BeanUtils.copyProperties(permission, entity, new String[] { "createUser",
				"createTime", "modifyUser", "modifyTime" });

		permissionService.save(permission);

		return "redirect:permission_list";
	}

	/**
	 * 进入列表
	 * 
	 * @param request
	 * @param model
	 * @param page
	 * @param permission
	 * @return
	 */
	@RequestMapping(value = "/permission_list")
	public String list(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "page", defaultValue = "1") int currentPage,
			Permission permission) {

		Page<Permission> page = new Page<Permission>();
		page.setCurrentPage(currentPage);
		// 设置查询条件
		page.setCriteria(permission);
		permissionService.page(page);
		model.put("page", page);
		return "admin/permission_list";
	}

	/**
	 * 批量删除
	 * 
	 * @param request
	 * @param model
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "/permission_delete")
	@ResponseBody
	public Result delete(HttpServletRequest request, ModelMap model, Long[] key) {
		try {
			permissionService.deleteByPrimaryKeys(key);
			return new Result();
		} catch (Exception e) {
			log.error("permission删除失败", e);
			return new Result(e);
		}
	}
}