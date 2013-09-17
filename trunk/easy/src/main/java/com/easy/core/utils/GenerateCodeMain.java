package com.easy.core.utils;

import codeworker.FreemarkerCoder;
import codeworker.config.ConfigPropertiesUtil;

/**
 * 生成代码的main方法类
 * */
public class GenerateCodeMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//使用方法：只需配置好代码生成框架中的code.properties和jdbc.properties即可
		//可以通过generateAll()生成所以文件，也可以通过分别调用生成相应部分
		//ConfigPropertiesUtil.setCodeProperties("codeworker/code.properties");
		String entityName="Role";
		String entityPackage="com.easy.admin.entity";
		String dao_package="com.easy.admin.dao";
		String daoimpl_package="com.easy.admin.dao.impl";
		String service_package="com.easy.admin.service";
		String serviceimpl_package="com.easy.admin.service.impl";
		String controller_package="com.easy.admin.controller";
		String tableName="role";
		String pkname="id";
		
//		FreemarkerCoder.execute_generateCode(entityName,entityPackage,dao_package,daoimpl_package,service_package,serviceimpl_package,controller_package);		
//		FreemarkerCoder.execute_generateMapperXml(entityName,tableName,pkname,dao_package);
//		FreemarkerCoder.execute_generateEntityClass(entityName,entityPackage,tableName);
//		FreemarkerCoder.execute_generatePages("Role", "role");//生成页面
		
		FreemarkerCoder.generateAll(entityName, entityPackage, dao_package, daoimpl_package, service_package, serviceimpl_package, controller_package, tableName, pkname);
	}

}
