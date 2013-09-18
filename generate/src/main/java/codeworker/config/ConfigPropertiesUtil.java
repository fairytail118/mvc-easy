/**
 * cxybj.com Copyright (c) 2012-2013 All Rights Reserved.
 */
package codeworker.config;

import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author wy
 * @version v 0.1 2013-6-2 下午1:36:07 wy Exp $
 */
public class ConfigPropertiesUtil {

	private static final Properties properties=new Properties();//代码生成相关配置
	
	//加载code.properties,初始化freemarker相关配置
	static{
		try{					
			properties.load(ClassLoader.getSystemResourceAsStream("code.properties"));
		}catch (Exception e) {
			System.err.println("从classpath根路径加载默认配置文件[code.properties]失败,需要指定code.properties的位置");
		}
	}
	
	/**设置代码生成属性文件的位置
	 * 从classpath中的指定位置加载代码生成的配置文件
	 * */
	public synchronized static void setCodeProperties(String codepropertiesLocation){
		try {
			properties.load(ClassLoader.getSystemResourceAsStream(codepropertiesLocation));
		} catch (IOException e) {
			System.err.println("加载codeproperties配置文件失败:"+e);
			System.exit(0);
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
