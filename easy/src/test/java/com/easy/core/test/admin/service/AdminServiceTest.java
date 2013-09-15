/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.test.admin.service;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;

import com.easy.admin.entity.Admin;
import com.easy.admin.service.AdminService;
import com.easy.core.test.EasyTest;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-10 下午8:32:46 wy Exp $
 */
public class AdminServiceTest extends EasyTest {

	@Resource
	private AdminService adminService;

	@Test
	public void testSave() {
		Admin admin = new Admin();

		admin.setName("test");

		admin.setLoginName("login");

		admin.setPassword("123456");

		admin.setIsEnabled(true);
		admin.setIsLocked(false);

		for (int i = 0; i < 10000; i++) {
			admin.setName(RandomStringUtils.random(10, "项目是一个定制框架它源自一个在线新闻Web站点于2005年以开源的形式被释放出来Django框架的核心组件有"));
			admin.setIsSuper(i % 5 == 0);
			admin.setId(null);
			adminService.save(admin);
		}
		// adminService.save(admin);

	}

}
