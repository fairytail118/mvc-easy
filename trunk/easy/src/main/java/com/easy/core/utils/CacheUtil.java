/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.utils;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 缓存工具
 * 
 * @author wy
 * @version v 0.1 2013-9-14 下午2:59:58 wy Exp $
 */
@Component
public class CacheUtil {

    /** 日志 */
    protected final static Logger log                = LoggerFactory.getLogger(CacheUtil.class);

    /** 对应的ehcache.xml中的缓存名称 */
    public final static String    DEFAULT_EASY_CACHE = "easyCache";

    /** 缓存 通过静态注入 */
    private static CacheManager   cacheManager;

    /**
     * 自动注入
     * 
     * @param cacheManager
     */
    @Autowired
    public void setCacheManager(CacheManager cacheManager) {
        CacheUtil.cacheManager = cacheManager;
    }

    /**
     * 获取配置的缓存
     * 
     * @param name
     * @return
     */
    public static Cache getCache(String name) {
        return cacheManager.getCache(name);
    }

    /**
     * 获取缓存值
     * 
     * @param name
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(String name, String key) {
        Element element = getCache(name).get(key);
        if (element != null) {
            return (T) element.getValue();
        }
        return null;
    }

    /**
     * 从缓存中移除
     * 
     * @param name
     * @param key
     */
    public static void remove(String name, String key) {
        getCache(name).remove(key);
    }

    /**
     * 设置缓存
     * 
     * @param name
     * @param key
     * @param value
     */
    public static void set(String name, String key, Serializable value) {

        if (value == null) {
            log.warn("设置缓存key:{}的value为空", key);
            return;
        }
        Cache cache = getCache(name);
        Element element = new Element(key, value);
        cache.put(element);
    }

    /**
     * 判断缓存是否存在
     * 
     * @param name
     * @param key
     * @return
     */
    public boolean exist(String name, String key) {
        return getCache(name).isKeyInCache(key);
    }

    /**
     * 获取缓存值
     * 
     * @param key
     * @return
     */
    public static <T> T get(String key) {
        return get(DEFAULT_EASY_CACHE, key);
    }

    /**
     * 从缓存中移除
     * 
     * @param key
     */
    public static void remove(String key) {
        remove(DEFAULT_EASY_CACHE, key);
    }

    /**
     * 设置缓存
     * 
     * @param key
     * @param value
     */
    public static void set(String key, Serializable value) {

        set(DEFAULT_EASY_CACHE, key, value);
    }

    /**
     * 判断缓存是否存在
     * 
     * @param key
     * @return
     */
    public boolean exist(String key) {
        return getCache(DEFAULT_EASY_CACHE).isKeyInCache(key);
    }

}
