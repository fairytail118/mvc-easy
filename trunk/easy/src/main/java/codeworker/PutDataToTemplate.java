package codeworker;

import java.util.Map;

/**
 * 此接口是向模板文件中添加自定义变量的回调方法
 * */
public interface PutDataToTemplate {
	
	/**
	 * @param dataMap 模板数据的map
	 * 要加入自己的数据变量的使用方法:dataMap.put("name","test");
	 * */
	void putData(Map<String, Object> dataMap);

}
