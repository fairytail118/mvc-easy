/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.test.admin.service;

import javax.annotation.Resource;

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

		adminService.save(admin);
		adminService.save(admin);
		

	}

}
