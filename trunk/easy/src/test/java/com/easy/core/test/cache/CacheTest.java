/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.test.cache;

import org.junit.Assert;
import org.junit.Test;

import com.easy.core.test.EasyTest;
import com.easy.core.utils.CacheUtils;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-14 下午3:18:28 wy Exp $
 */
public class CacheTest extends EasyTest {

	@Test
	public void test() {

		String value = "123";

		CacheUtils.set("test", value);

		Assert.assertEquals(value, CacheUtils.get("test"));

	}

	@Test
	public void testRestart() {

		String value = "123";

		Assert.assertEquals(value, CacheUtils.get("test"));

	}

}
