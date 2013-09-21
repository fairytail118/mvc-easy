package com.easy.core.spring;

import java.util.List;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.easy.core.utils.PropertyEncoder;

/**
 * 支持对属性文件中的某些属性值进行转换
 * 主要目的是对属性文件中的敏感信息加密解密
 * @author xieqiang
 * 2013-9-19
 * */
public class SupportConvertPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{
	private List<String> convertKeyList;//需要转换的属性
	private boolean debug;

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if(debug){
			return propertyValue;
		}
		if(convertKeyList==null || convertKeyList.isEmpty())return propertyValue;		
		for(String key : convertKeyList){
			if(key.equals(propertyName)){
				return PropertyEncoder.decode(propertyValue);
			}
		}
		 return propertyValue;
		
	}
	
	public List<String> getConvertKeyList() {
		return convertKeyList;
	}

	public void setConvertKeyList(List<String> convertKeyList) {
		this.convertKeyList = convertKeyList;
	}
	
	public boolean getDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

}
