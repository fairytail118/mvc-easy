/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.validator;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author wy
 * @version v 0.1 2013-9-10 下午10:59:41 wy Exp $
 */
public interface FieldValidator<T> {

    /**
     * 验证,从页面过来的都是string
     * 
     * @param validator
     * @param request
     * @return
     */
    ValidatorResult isValid(T validator, HttpServletRequest request);

}
