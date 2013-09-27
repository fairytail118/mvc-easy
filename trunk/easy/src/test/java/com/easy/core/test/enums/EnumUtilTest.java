/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.test.enums;

import org.junit.Assert;
import org.junit.Test;

import com.easy.admin.enums.UserType;
import com.easy.core.enums.EnumsUtil;

/**
 * 枚举工具类测试
 * 
 * @author wy
 * @version v 0.1 2013-9-27 下午9:55:16 wy Exp $
 */
public class EnumUtilTest {

    @Test
    public void test_valueOf() {

        UserType userType = EnumsUtil.valueOf(UserType.class, "user");

        Assert.assertTrue(userType == UserType.user);

    }

    @Test
    public void test_valueOfIgnoreCase() {

        UserType userType = EnumsUtil.valueOfIgnoreCase(UserType.class, "user");

        Assert.assertTrue(userType == UserType.user);

    }

    @Test
    public void test_valueOfNull() {

        UserType userType = EnumsUtil.valueOf(UserType.class, "User");

        Assert.assertTrue(userType == null);

    }

}
