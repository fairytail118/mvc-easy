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

import com.easy.admin.entity.Resource;
import com.easy.admin.service.ResourceService;
import com.easy.core.common.Page;
import com.easy.core.controller.BaseController;
import com.easy.core.mvc.result.Result;
import com.easy.core.utils.RequestUtil;
import com.easy.core.validator.annotations.RequiredStringValidator;
import com.easy.core.validator.annotations.Validations;

/**
 * 资源
 * 
 * @author wy
 * @version v 0.1 2013-6-16 下午10:26:54 wy Exp $
 */
@Controller
@RequestMapping("/admin")
public class ResourceController extends BaseController {

	/** 资源service */
	@Autowired
	private ResourceService resourceService;

	/**
	 * 进入编辑页面
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/resource_input")
	public String input(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "id", required = false) Long id) {

		if (id != null) {
			Resource resource = resourceService.getByPrimaryKey(id);
			model.put("resource", resource);
		}
		else{
			model.put("resource", new Resource());
		}

		return "admin/resource_input";
	}

	/**
	 * 进入保存
	 * 
	 * @param request
	 * @param model
	 * @param resource
	 * @return
	 */
	@Validations(
	
		requiredStringValidators = { 
								@RequiredStringValidator(field = "name", message = "名称不能为空!", trim = true),
						@RequiredStringValidator(field = "url", message = "URL不能为空!", trim = true),
						@RequiredStringValidator(field = "isSystem", message = "是否系统不能为空!", trim = true),
						@RequiredStringValidator(field = "groupCode", message = "组编码不能为空!", trim = true)		
			}
	)
	@RequestMapping(value = "/resource_save")
	public String save(HttpServletRequest request, ModelMap model, Resource resource) {
		if (RequestUtil.hasErrors(request)) {
			return "admin/resource_input";
		}

		Resource entity = new Resource();
		//拷贝，去掉重复的
		BeanUtils.copyProperties(resource, entity, new String[] { "createUser",
				"createTime", "modifyUser", "modifyTime" });

		resourceService.save(resource);

		return "redirect:resource_list";
	}

	/**
	 * 进入列表
	 * 
	 * @param request
	 * @param model
	 * @param page
	 * @param resource
	 * @return
	 */
	@RequestMapping(value = "/resource_list")
	public String list(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "page", defaultValue = "1") int currentPage,
			Resource resource) {

		Page<Resource> page = new Page<Resource>();
		page.setCurrentPage(currentPage);
		// 设置查询条件
		page.setCriteria(resource);
		resourceService.page(page);
		model.put("page", page);
		return "admin/resource_list";
	}

	/**
	 * 批量删除
	 * 
	 * @param request
	 * @param model
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "/resource_delete")
	@ResponseBody
	public Result delete(HttpServletRequest request, ModelMap model, Long[] key) {
		try {
			resourceService.deleteByPrimaryKeys(key);
			return new Result();
		} catch (Exception e) {
			log.error("resource删除失败", e);
			return new Result(e);
		}
	}
}