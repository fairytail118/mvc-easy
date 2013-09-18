package codeworker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import codeworker.config.ConfigPropertiesUtil;
import codeworker.db.TableUtil;
import codeworker.db.model.Column;
import codeworker.db.model.Table;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerCoder {
	
	private FreemarkerCoder(){}
	private static Configuration configuration=new Configuration();
	
	//加载code.properties,初始化freemarker相关配置
	static{
		try{					
			configuration.setNumberFormat("#");
			configuration.setDirectoryForTemplateLoading(new File(ConfigPropertiesUtil.get("freemarker.templateDirectory")));
			configuration.setEncoding(Locale.getDefault(),ConfigPropertiesUtil.get("freemarker.encoding"));
			configuration.setOutputEncoding(ConfigPropertiesUtil.get("freemarker.encoding"));
		}catch (Exception e) {
			System.err.println("freemarker 初始化异常:"+e);
			System.exit(0);
		}
	}

	//生成文件的基础方法
	private static void generateCode(String templateFile,String outFilePath,String filename,Map<String, Object> data){
		try {			
			Template template=configuration.getTemplate(templateFile, ConfigPropertiesUtil.get("freemarker.encoding"));
			File file=new File(outFilePath);
			if(!file.exists()){
				file.mkdirs();
			}
			Writer writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file+File.separator+filename)));		
			template.process(data, writer);		
			System.err.println("文件"+filename+"生成成功...");
		} catch (Exception e) {
			System.err.println("自动生成文件"+filename+"异常:"+e);
		} 
		
	}
	
	//生成Java文件(不含entity类)的相关数据变量
	private static Map<String, Object> createJavaDataMap(String entityName,String entity_package,String dao_package,String daoimpl_package,String service_package,String serviceimpl_package,String controller_package){
		Map<String, Object> datas=new HashMap<String, Object>();
		datas.put("date",new Date().toLocaleString());
		datas.put("controller_package", controller_package);		
		datas.put("entity_package", entity_package);
		datas.put("dao_package", dao_package);
		datas.put("daoimpl_package", daoimpl_package);
		datas.put("service_package", service_package);
		datas.put("serviceimpl_package", serviceimpl_package);
		datas.put("entity", entityName);
		datas.put("author", "xieqiang");
		String lower_entity=entityName.substring(0, 1).toLowerCase()+entityName.substring(1);//实体类名的第一个字符给为小写
		datas.put("lower_entity", lower_entity);
		
		return datas;
	}
	
	//生成实体类文件的相关数据变量
	private static Map<String, Object> createEntityDataMap(String entityName,String entity_package,String tableName){
		Map<String, Object> datas=new HashMap<String, Object>();
		Table table=TableUtil.getTable(tableName);
		datas.put("entity_package", entity_package);
		datas.put("entity", entityName);
		datas.put("entity_comment", table.getDesc());
		datas.put("date",new Date().toLocaleString());
		datas.put("serialVersionUID", new Random().nextLong());
		datas.put("author", "xieqiang");
		String javatype_declareString=generateJavaTypeDeclareString(table);//实体类中的字段声明字符串部分
		datas.put("entity_content", javatype_declareString);
		return datas;
	}
	
	
	//生成mapper.xml文件的相关数据变量
	private static Map<String, Object> createMapperXmlDataMap(String entityName,String entityTableName,String pkname,String dao_package){
		Map<String, Object> datas=new HashMap<String, Object>();
		datas.put("dao_package", dao_package);
		datas.put("entity", entityName);
		String lower_entity=entityName.substring(0, 1).toLowerCase()+entityName.substring(1);//实体类名的第一个字符给为小写
		datas.put("lower_entity", lower_entity);
		datas.put("pkname", pkname);
		Map<String, String> colum=MybatisMapperFileUtil.getTableCloums(entityTableName);
		datas.put("insert_sql", MybatisMapperFileUtil.getMybatisInsertSql(colum, entityTableName, pkname));
		datas.put("update_sql", MybatisMapperFileUtil.getMybatisUpdateSql(colum, entityTableName, pkname));
		datas.put("delete_sql", MybatisMapperFileUtil.getMybatisDeleteSql(entityTableName, pkname));
		datas.put("select_sql", MybatisMapperFileUtil.getMybatisSelectSql(entityTableName, pkname));
		datas.put("cloumns", MybatisMapperFileUtil.getCloumnStr(colum));
		datas.put("resultMap", MybatisMapperFileUtil.getResultMap(colum, pkname));
		return datas;
	}
	
	//生成实体类中的类型声明和getter/setter字符串
	private static String generateJavaTypeDeclareString(Table table){
		StringBuilder str=new StringBuilder(150);
		List<Column> columns=table.getColumnList();
		
		String cloum=null;
		String cloum_type=null;
		for(Column item : columns){
			cloum=item.getColumn();
			if(cloum.equalsIgnoreCase("id") || cloum.equalsIgnoreCase("createUser") || cloum.equalsIgnoreCase("createTime") || cloum.equalsIgnoreCase("modifyUser") || cloum.equalsIgnoreCase("modifyTime")){
				continue;//BaseEntity中的字段不需要再写
			}
			cloum_type=item.getTypeClass();
			str.append("\t/**\n\t*").append(item.getComment()).append("\n\t*/\n").append("\t private ").append(cloum_type).append(" ").append(cloum).append(";\n");
		}
		str.append("\n");
		//getter/setter
		
		String upperCloumName=null;//字段的第一个字母改成大写
		for(Column item : columns){
			cloum=item.getColumn();
			if(cloum.equalsIgnoreCase("id") || cloum.equalsIgnoreCase("createUser") || cloum.equalsIgnoreCase("createTime") || cloum.equalsIgnoreCase("modifyUser") || cloum.equalsIgnoreCase("modifyTime")){
				continue;//BaseEntity中的字段不需要再写
			}
			cloum_type=item.getTypeClass();
			upperCloumName=cloum.substring(0, 1).toUpperCase()+cloum.substring(1);
			str.append("\t/**\n\t*").append(item.getComment()).append("\n\t*/\n").append("\tpublic ").append(cloum_type).append(" get").append(upperCloumName).append("(){\n\t\treturn ").append(cloum).append(";\n\t}\n");
			str.append("\t/**\n\t*").append(item.getComment()).append("\n\t*/\n").append("\tpublic void set").append(upperCloumName).append("(").append(cloum_type).append(" ").append(cloum).append(") {\n\t\t").append("this.").append(cloum).append("=").append(cloum).append(";\n\t}\n");
		}
		return str.toString();
	}
	
	
	
	//创建页面需要的模板数据
	private static Map<String, Object> createPageDataMap(String entityName,String tableName){
		Map<String, Object> datas=new HashMap<String, Object>();
		datas.put("entity", entityName);
		datas.put("tableName", tableName);
		String lower_entity=entityName.substring(0, 1).toLowerCase()+entityName.substring(1);//实体类名的第一个字符给为小写
		datas.put("lower_entity", lower_entity);
		datas.put("colums", TableUtil.getTable(tableName).getColumnList());
		return datas;
	}
	
	//获取各种操作系统下的文件分隔符的正则表达式字符串，主要是处理window系统下文件分隔符与正则表达式冲突的问题
	private static String getFileseparatorRegex(){
		String fileseparator_regex;//文件分隔符的正则表达式表示形式,用于将包名中的.替换成文件分隔符用
		String osname=System.getProperties().getProperty("os.name");
		if(osname.toLowerCase().indexOf("win")!=-1){
			fileseparator_regex="\\"+"\\";//windows系统需特别处理
		}else {
			fileseparator_regex=File.separator;
		}
		return fileseparator_regex;
	}
	
	
	
	/*=========================自动生成代码文件所要调用的方法==================================================*/
	
	/**
	 * 根据code.properties配置生成实体类相关文件目录及java文件,包含dao,service,controller
	 * @param entityName 实体类名称，如Role
	 * @param entity_package 该实体类所在的包名,如com.easy.role.entity
	 * @param dao_package 该实体类对应的dao接口所在的包名,如com.easy.role.dao
	 * @param daoimpl_package  该实体类对应的dao实现类所在的包名,如com.easy.role.dao.impl
	 * @param service_package 该实体类对应的service接口所在的包名,如com.easy.role.service
	 * @param serviceimpl_package  该实体类对应的service实现类所在的包名,如com.easy.role.service.impl
	 * @param controller_package 该实体类对应的controller所在的包名,如com.easy.role.controller
	 * */
	public static void execute_generateCode(String entityName,String entity_package,String dao_package,String daoimpl_package,String service_package,String serviceimpl_package,String controller_package,PutDataToTemplate callback){
		String basepath=ConfigPropertiesUtil.get("project.src.realpath");//.java文件的基准目录
		final Map<String, Object> datas=createJavaDataMap(entityName, entity_package, dao_package, daoimpl_package, service_package, serviceimpl_package, controller_package);
		if(callback!=null){
			callback.putData(datas);
		}
		String fileseparator_regex;//文件分隔符的正则表达式表示形式,用于将包名中的.替换成文件分隔符用
		String osname=System.getProperties().getProperty("os.name");
		if(osname.toLowerCase().indexOf("win")!=-1){
			fileseparator_regex="\\"+"\\";//windows系统需特别处理
		}else {
			fileseparator_regex=File.separator;
		}
		
		//生成dao接口
		String dao_outpath;
		String daopackge_topath=dao_package.replaceAll("\\.", fileseparator_regex);//将包名中的"."变成文件目录分隔符
		if(basepath.endsWith(File.separator)){
			dao_outpath=basepath +daopackge_topath ;
		}else {
			dao_outpath=basepath+File.separator+daopackge_topath;
		}
		generateCode(ConfigPropertiesUtil.get("freemarker.dao.filename"), dao_outpath, entityName+"Dao.java", datas);
		
		//生成daoimp
		String daoimpl_outpath;
		String daoimppackage_topath=daoimpl_package.replaceAll("\\.", fileseparator_regex);
		if(basepath.endsWith(File.separator)){
			daoimpl_outpath=basepath+daoimppackage_topath;
		}else {
			daoimpl_outpath=basepath+File.separator+daoimppackage_topath;
		}
		generateCode(ConfigPropertiesUtil.get("freemarker.daoimpl.filename"), daoimpl_outpath, entityName+"DaoImpl.java", datas);
		
		//生成service接口
		String service_outpath;
		String servicepackage_topath=service_package.replaceAll("\\.", fileseparator_regex);
		if(basepath.endsWith(File.separator)){
			service_outpath=basepath+servicepackage_topath;
		}else {
			service_outpath=basepath+File.separator+servicepackage_topath;
		}
		generateCode(ConfigPropertiesUtil.get("freemarker.service.filename"), service_outpath, entityName+"Service.java", datas);
		
		//生成serviceimpl
		String serviceimpl_outpath;
		String serviceimplpackage_topath=serviceimpl_package.replaceAll("\\.", fileseparator_regex);
		if(basepath.endsWith(File.separator)){
			serviceimpl_outpath=basepath+serviceimplpackage_topath;
		}else {
			serviceimpl_outpath=basepath+File.separator+serviceimplpackage_topath;
		}
		generateCode(ConfigPropertiesUtil.get("freemarker.serviceimpl.filename"), serviceimpl_outpath, entityName+"ServiceImpl.java", datas);
		
		//生成controller
		String controller_outpath;
		String controllerpackage_topath=controller_package.replaceAll("\\.", fileseparator_regex);
		if(basepath.endsWith(File.separator)){
			controller_outpath=basepath+controllerpackage_topath;
		}else {
			controller_outpath=basepath+File.separator+controllerpackage_topath;
		}
		generateCode(ConfigPropertiesUtil.get("freemarker.controller.filename"), controller_outpath, entityName+"Controller.java", datas);
		
	}
	
	public static void execute_generateCode(String entityName,String entity_package,String dao_package,String daoimpl_package,String service_package,String serviceimpl_package,String controller_package){
		execute_generateCode(entityName, entity_package, dao_package, daoimpl_package, service_package, serviceimpl_package, controller_package,null);
	}
	
	/**
	 * 生成实体类的源文件(.java)
	 * @param entityName 实体类名称，如Role
	 * @param entity_package 该实体类所在的包名,如com.easy.role.entity
	 * @param tableName 实体类对应的数据表名
	 * */
	public static void execute_generateEntityClass(String entityName,String entity_package,String tableName,PutDataToTemplate callback){
		String basepath=ConfigPropertiesUtil.get("project.src.realpath");//.java文件的基准目录
		final Map<String, Object> datas=createEntityDataMap(entityName, entity_package, tableName);
		if(callback!=null){
			callback.putData(datas);
		}
		String entity_outpath=null;
		String fileseparator_regex=getFileseparatorRegex();
		String entitypackage_topath=entity_package.replaceAll("\\.", fileseparator_regex);
		if(basepath.endsWith(File.separator)){
			entity_outpath=basepath+entitypackage_topath;
		}else {
			entity_outpath=basepath+File.separator+entitypackage_topath;
		}
		generateCode(ConfigPropertiesUtil.get("freemarker.entity.filename"), entity_outpath, entityName+".java", datas);
		
	}
	
	public static void execute_generateEntityClass(String entityName,String entity_package,String tableName){
		execute_generateEntityClass(entityName, entity_package, tableName,null);
	}
	
	/**
	 * 生成mapper.xml
	 * * @param entityName 实体类名称，如Role
	 * @param entityTableName 实体类对应的表名
	 * @param pkname 该表的主键字段名
	 * @param dao_package 该实体类对应的dao接口所在的包名,如com.easy.role.dao
	 * */
	public static void execute_generateMapperXml(String entityName,String entityTableName,String pkname,String dao_package,PutDataToTemplate callback){
		final Map<String, Object> datas=createMapperXmlDataMap(entityName, entityTableName, pkname, dao_package);
		if(callback!=null){
			callback.putData(datas);
		}
		generateCode(ConfigPropertiesUtil.get("freemarker.mapper.filename"), ConfigPropertiesUtil.get("mapper.xml.realpath"), entityName+"Mapper.xml", datas);		
	}
	
	public static void execute_generateMapperXml(String entityName,String entityTableName,String pkname,String dao_package){
		execute_generateMapperXml(entityName, entityTableName, pkname, dao_package, null);
	}
	
	
	/**
	 * 生成页面
	 * @param entityName 实体类名称，如Role
	 *  @param tableName 实体类对应的表名
	 * */
	public static void execute_generatePages(String entityName,String tableName,PutDataToTemplate callback){
		final Map<String, Object> datas=createPageDataMap(entityName, tableName);
		if(callback!=null){
			callback.putData(datas);
		}
		String lower_entity=entityName.substring(0, 1).toLowerCase()+entityName.substring(1);
		generateCode(ConfigPropertiesUtil.get("freemarker.adminmanage.page.inputpage.filename"), ConfigPropertiesUtil.get("pages.adminmanage.realpath"), lower_entity+"_input.ftl", datas);
		generateCode(ConfigPropertiesUtil.get("freemarker.adminmanage.page.listpage.filename"), ConfigPropertiesUtil.get("pages.adminmanage.realpath"), lower_entity+"_list.ftl", datas);
	}
	
	public static void execute_generatePages(String entityName,String tableName){
		execute_generatePages(entityName, tableName, null);
	}
	
	
	/***
	 * 生成一个在数据库中存在的指定的表的所有文件，包括实体类文件、dao、service、controller、mybatis映射文件
	 * @param entityName 实体类名称，如Role
	 * @param entity_package 该实体类所在的包名,如com.easy.role.entity
	 * @param dao_package 该实体类对应的dao接口所在的包名,如com.easy.role.dao
	 * @param daoimpl_package  该实体类对应的dao实现类所在的包名,如com.easy.role.dao.impl
	 * @param service_package 该实体类对应的service接口所在的包名,如com.easy.role.service
	 * @param serviceimpl_package  该实体类对应的service实现类所在的包名,如com.easy.role.service.impl
	 * @param controller_package 该实体类对应的controller所在的包名,如com.easy.role.controller
	 * @param tableName 实体类对应的表名
	 * @param pkname 表中的主键字段名称
	 */
	
	public static void generateAll(String entityName,String entity_package,String dao_package,String daoimpl_package,String service_package,String serviceimpl_package,String controller_package,String tableName,String pkname){
		execute_generateEntityClass(entityName, entity_package, tableName,null);
		execute_generateCode(entityName, entity_package, dao_package, daoimpl_package, service_package, serviceimpl_package, controller_package,null);
		execute_generateMapperXml(entityName, tableName, pkname, dao_package,null);
		StringBuilder stringBuilder=new StringBuilder(100);
		stringBuilder.append("<typeAlias alias=\""+entityName+"\" type=\""+entity_package+"."+entityName+"\" />");
		stringBuilder.append("<mapper resource=\"mapper/admin/"+entityName+"Mapper.xml\" />");
		System.err.println("mybatis.xml中要加上的配置:"+stringBuilder);
		
		execute_generatePages(entityName,tableName,null);
	}
	
}
