/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.security;

import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 加密混淆的值
 * 
 * @author wy
 * @version v 0.1 2013-9-24 下午11:04:28 wy Exp $
 */
@Component("easySaltSource")
public class EasySaltSource implements SaltSource {

    /** 
     * @see org.springframework.security.authentication.dao.SaltSource#getSalt(org.springframework.security.core.userdetails.UserDetails)
     */
    @Override
    public Object getSalt(UserDetails user) {
        return user.getUsername();
    }

}
