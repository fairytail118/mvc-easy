package com.easy.core.utils;

import org.springframework.security.core.codec.Base64;

/**
 * 对属性文件进行简单的加密解密.只需避免太过暴露明文，故只进行简单加密，再次使用base64编码
 * */
public class PropertyEncoder {

	public static String encode(String value){
		
		return new String(Base64.encode(value.getBytes()));
	}
	
	public static String decode(String value){
		return new String(Base64.decode(value.getBytes()));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("password:"+decode("cm9vdA"));

	}

}
