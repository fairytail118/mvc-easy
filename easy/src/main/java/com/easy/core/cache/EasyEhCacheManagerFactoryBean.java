/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.cache;

import java.io.IOException;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;

/**
 * EhCacheManager缓存代理，重写EhCacheManagerFactoryBean，主要是为了重启时能读取缓存
 * 
 * @author wy
 * @version v 0.1 2013-9-14 下午3:46:59 wy Exp $
 */
public class EasyEhCacheManagerFactoryBean extends EhCacheManagerFactoryBean
                                                                            implements
                                                                            FactoryBean<CacheManager>,
                                                                            InitializingBean,
                                                                            DisposableBean {

    /** 是否从硬盘加载文件 */
    private boolean initFromDisk = true;

    /**
     * Setter method for property <tt>initFromDisk</tt>.
     * 
     * @param initFromDisk value to be assigned to property initFromDisk
     */
    public void setInitFromDisk(boolean initFromDisk) {
        this.initFromDisk = initFromDisk;
    }

    /**
     * 
     * @see org.springframework.cache.ehcache.EhCacheManagerFactoryBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws IOException, CacheException {
        // 重启时读取
        System.setProperty("net.sf.ehcache.enableShutdownHook", String.valueOf(initFromDisk));
        super.afterPropertiesSet();
    }

}
