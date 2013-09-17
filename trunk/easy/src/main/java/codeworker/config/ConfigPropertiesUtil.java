/**
 * cxybj.com Copyright (c) 2012-2013 All Rights Reserved.
 */
package codeworker.config;

import java.util.Properties;
import codeworker.FreemarkerCoder;

/**
 * 
 * @author wy
 * @version v 0.1 2013-6-2 下午1:36:07 wy Exp $
 */
public class ConfigPropertiesUtil {

	private static Properties properties=new Properties();//代码生成相关配置
	
	//加载code.properties,初始化freemarker相关配置
	static{
		try{					
			properties.load(FreemarkerCoder.class.getResourceAsStream("code.properties"));
		}catch (Exception e) {
			System.err.println(e);
		}
	}
    /**
     * 获取配置文件
     * 
     * @param key
     * @return
     */
    public static String get(String key) {
      return properties.getProperty(key);
    }
    
    
}
