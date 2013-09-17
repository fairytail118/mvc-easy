/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package ${controller_package};

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ${entity_package}.${entity};
import ${service_package}.${entity}Service;
import com.easy.core.common.Page;
import com.easy.core.controller.BaseController;
import org.springframework.beans.BeanUtils;
import com.easy.core.mvc.result.Result;
import com.easy.core.utils.RequestUtil;

/**
 * 
 * @author 
 * @version v 0.1 ${date}
 */
@Controller
@RequestMapping("/${lower_entity}")
public class ${entity}Controller extends BaseController {

	@Autowired
	private ${entity}Service ${lower_entity}Service;

	/**
	 * 进入编辑或者新增
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/${lower_entity}_input")
	public String input(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "id", required = false) Long id) {

		if (id != null) {
			${entity} ${lower_entity} = ${lower_entity}Service.getByPrimaryKey(id);
			model.put("${lower_entity}", ${lower_entity});
		}

		return "${lower_entity}/${lower_entity}_input";
	}

	/**
	 * 进入保存
	 * 
	 * @param request
	 * @param model
	 * @param ${lower_entity}
	 * @return
	 */
	
	@RequestMapping(value = "/${lower_entity}_save")
	public String save(HttpServletRequest request, ModelMap model, ${entity} ${lower_entity}) {
		if (RequestUtil.hasErrors(request)) {
			return "${lower_entity}/${lower_entity}_input";
		}
		${entity} entity=new ${entity}();
		//拷贝，去掉重复的
		BeanUtils.copyProperties(${lower_entity}, entity, new String[] { "createUser",
				"createTime", "modifyUser", "modifyTime" });
		${lower_entity}Service.save(${lower_entity});

		return "redirect:${lower_entity}_list";
	}

	/**
	 * 进入列表
	 * @param request
	 * @param model
	 * @param page
	 * @param ${lower_entity}
	 * @return
	 */
	@RequestMapping(value = "/${lower_entity}_list")
	public String list(HttpServletRequest request, ModelMap model,
			@RequestParam(value = "page", defaultValue = "1") int currentPage,
			${entity} ${lower_entity}) {

		Page<${entity}> page = new Page<${entity}>();
		page.setCurrentPage(currentPage);
		// 设置查询条件
		page.setCriteria(${lower_entity});
		${lower_entity}Service.page(page);
		model.put("page", page);
		return "${lower_entity}/${lower_entity}_list";
	}

	/**
	 * 批量删除
	 * 
	 * @param request
	 * @param model
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Result delete(HttpServletRequest request, ModelMap model, Long[] key) {
		try {
			${lower_entity}Service.deleteByPrimaryKeys(key);
			return new Result();
		} catch (Exception e) {
			log.error("${lower_entity}删除失败", e);
			return new Result(e.getMessage());
		}
	}
}
