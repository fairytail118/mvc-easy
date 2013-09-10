/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.test;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基础的测试代码
 * 
 * @author wy
 * @version v 0.1 2013-9-10 下午8:25:04 wy Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/*.xml" })
public abstract class EasyTest {

	protected Logger log = LoggerFactory.getLogger(getClass());

	@Before
	public void before() {
		log.info("\n-------------Start Junit Test-----------------------------------");
	}

	@After
	public void after() {
		log.info("\n-------------Finished Junit Test-----------------------------------");
	}
}