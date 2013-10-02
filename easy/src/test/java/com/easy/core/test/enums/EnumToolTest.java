/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.test.enums;

import org.junit.Test;

import junit.framework.Assert;

import com.easy.admin.enums.UserType;
import com.easy.core.enums.IEnum;
import com.easy.core.mvc.freemarker.tools.EnumTool;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-27 下午11:05:10 wy Exp $
 */
public class EnumToolTest {

    
    @Test
    public void test() {

        IEnum ie = new EnumTool().valueOf(UserType.class.getName(), "user");

        Assert.assertNotNull(ie);

    }
}
