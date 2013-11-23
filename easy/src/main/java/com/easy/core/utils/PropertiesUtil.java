/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 资源文件加载
 * 
 * @author wy
 * @version v 0.1 2013-11-19 下午10:35:09 wy Exp $
 */
public class PropertiesUtil {

    /** 日志 */
    private final static Logger log = LoggerFactory.getLogger(PropertiesUtil.class);

    /**
     * 加载资源文件
     * 
     * @param file
     * @return
     */
    public static Map<String, String> loadResource(String properties) {

        Map<String, String> resourceMap = new HashMap<String, String>();

        String classPath = "/";

        try {
            classPath = Thread.currentThread().getContextClassLoader().getResource("").toURI()
                .getPath();
        }
        catch (URISyntaxException e) {
            log.warn("获取classpath路径错误", e);
        }

        String path = classPath
                      + (properties.startsWith(File.separator) ? properties : File.separator
                                                                              + properties);
        File file = new File(path);
        if (!file.exists()) {
            log.warn("从{}加载不到资源文件", path);
            return resourceMap;
        }
        Properties props = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            props.load(fis);
        }
        catch (FileNotFoundException e) {
            log.error("找不到资源文件", e);
        }
        catch (IOException e) {
            log.error("加载资源文件文件流异常", e);
        }
        finally {
            if (fis != null) {
                try {
                    fis.close();
                }
                catch (IOException e) {
                }
            }
        }

        Enumeration<Object> es = props.keys();

        while (es.hasMoreElements()) {
            String key = (String) es.nextElement();
            resourceMap.put(key, props.getProperty(key, ""));
        }

        return resourceMap;
    }
}
