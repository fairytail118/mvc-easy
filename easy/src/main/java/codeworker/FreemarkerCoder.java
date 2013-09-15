package codeworker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerCoder {
	
	private FreemarkerCoder(){}
	private static Configuration configuration=new Configuration();
	private static Properties properties=new Properties();//代码生成相关配置
	
	//加载code.properties,初始化freemarker相关配置
	static{
		try{					
			properties.load(FreemarkerCoder.class.getResourceAsStream("code.properties"));
			configuration.setDirectoryForTemplateLoading(new File(properties.getProperty("freemarker.templateDirectory")));
			configuration.setEncoding(Locale.getDefault(),properties.getProperty("freemarker.encoding"));
		}catch (Exception e) {
			System.err.println("freemarker 初始化异常:"+e);
		}
	}
	
	private static final String basepath=properties.getProperty("project.src.realpath");//.java文件的基准目录

	//生成文件的基础方法
	private static void generateCode(String templateFile,String outFilePath,String filename,Map<String, String> data){
		try {			
			Template template=configuration.getTemplate(templateFile, properties.getProperty("freemarker.encoding"));
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
	private static Map<String, String> createJavaDataMap(String entityName,String entity_package,String dao_package,String daoimpl_package,String service_package,String serviceimpl_package,String controller_package){
		Map<String, String> datas=new HashMap<String, String>();
		datas.put("date",new Date().toLocaleString());
		datas.put("controller_package", controller_package);		
		datas.put("entity_package", entity_package);
		datas.put("dao_package", dao_package);
		datas.put("daoimpl_package", daoimpl_package);
		datas.put("service_package", service_package);
		datas.put("serviceimpl_package", serviceimpl_package);
		datas.put("entity", entityName);
		String lower_entity=entityName.substring(0, 1).toLowerCase()+entityName.substring(1);//实体类名的第一个字符给为小写
		datas.put("lower_entity", lower_entity);
		
		return datas;
	}
	
	//生成实体类文件的相关数据变量
	private static Map<String, String> createEntityDataMap(String entityName,String entity_package,String tableName){
		Map<String, String> datas=new HashMap<String, String>();
		datas.put("entity_package", entity_package);
		datas.put("entity", entityName);
		datas.put("date",new Date().toLocaleString());
		String javatype_declareString=generateJavaTypeDeclareString(JdbcTableMetedataReader.getTableCloums(tableName));//实体类中的字段声明字符串部分
		datas.put("entity_content", javatype_declareString);
		return datas;
	}
	
	
	//生成mapper.xml文件的相关数据变量
	private static Map<String, String> createMapperXmlDataMap(String entityName,String entityTableName,String pkname,String dao_package){
		Map<String, String> datas=new HashMap<String, String>();
		datas.put("dao_package", dao_package);
		datas.put("entity", entityName);
		String lower_entity=entityName.substring(0, 1).toLowerCase()+entityName.substring(1);//实体类名的第一个字符给为小写
		datas.put("lower_entity", lower_entity);
		datas.put("pkname", pkname);
		Map<String, String> colum=JdbcTableMetedataReader.getTableCloums(entityTableName);
		datas.put("insert_sql", JdbcTableMetedataReader.getMybatisInsertSql(colum, entityTableName, pkname));
		datas.put("update_sql", JdbcTableMetedataReader.getMybatisUpdateSql(colum, entityTableName, pkname));
		datas.put("delete_sql", JdbcTableMetedataReader.getMybatisDeleteSql(entityTableName, pkname));
		datas.put("select_sql", JdbcTableMetedataReader.getMybatisSelectSql(entityTableName, pkname));
		datas.put("cloumns", JdbcTableMetedataReader.getCloumnStr(colum));
		datas.put("resultMap", JdbcTableMetedataReader.getResultMap(colum, pkname));
		return datas;
	}
	
	//生成实体类中的类型声明和getter/setter字符串
	private static String generateJavaTypeDeclareString(Map<String, String> cloums){
		StringBuilder str=new StringBuilder(150);
		Iterator<String> cloumSet=cloums.keySet().iterator();
		String cloum=null;
		String cloum_type=null;
		String javatype=null;
		while(cloumSet.hasNext()){
			cloum=cloumSet.next();
			if(cloum.equalsIgnoreCase("id") || cloum.equalsIgnoreCase("createUser") || cloum.equalsIgnoreCase("createTime") || cloum.equalsIgnoreCase("modifyUser") || cloum.equalsIgnoreCase("modifyTime")){
				continue;//BaseEntity中的字段不需要再写
			}
			cloum_type=cloums.get(cloum);
			javatype=DbTypeToJavaTypeSimpleFactory.dbTypeStringToJavaTypeString(cloum_type);
			str.append("\tprivate ").append(javatype).append(" ").append(cloum).append(";\n");
		}
		str.append("\n");
		//getter/setter
		cloumSet=cloums.keySet().iterator();
		String upperCloumName=null;//字段的第一个字母改成大写
		while(cloumSet.hasNext()){
			cloum=cloumSet.next();
			if(cloum.equalsIgnoreCase("id") || cloum.equalsIgnoreCase("createUser") || cloum.equalsIgnoreCase("createTime") || cloum.equalsIgnoreCase("modifyUser") || cloum.equalsIgnoreCase("modifyTime")){
				continue;//BaseEntity中的字段不需要再写
			}
			cloum_type=cloums.get(cloum);
			javatype=DbTypeToJavaTypeSimpleFactory.dbTypeStringToJavaTypeString(cloum_type);
			upperCloumName=cloum.substring(0, 1).toUpperCase()+cloum.substring(1);
			str.append("\tpublic ").append(javatype).append(" get").append(upperCloumName).append("(){\n\t\treturn ").append(cloum).append(";\n\t}\n");
			str.append("\tpublic void set").append(upperCloumName).append("(").append(javatype).append(" ").append(cloum).append(") {\n\t\t").append("this.").append(cloum).append("=").append(cloum).append(";\n\t}\n");
		}
		return str.toString();
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
	public static void execute_generateCode(String entityName,String entity_package,String dao_package,String daoimpl_package,String service_package,String serviceimpl_package,String controller_package){
		Map<String, String> datas=createJavaDataMap(entityName, entity_package, dao_package, daoimpl_package, service_package, serviceimpl_package, controller_package);
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
		generateCode(properties.getProperty("freemarker.dao.filename"), dao_outpath, entityName+"Dao.java", datas);
		
		//生成daoimp
		String daoimpl_outpath;
		String daoimppackage_topath=daoimpl_package.replaceAll("\\.", fileseparator_regex);
		if(basepath.endsWith(File.separator)){
			daoimpl_outpath=basepath+daoimppackage_topath;
		}else {
			daoimpl_outpath=basepath+File.separator+daoimppackage_topath;
		}
		generateCode(properties.getProperty("freemarker.daoimpl.filename"), daoimpl_outpath, entityName+"DaoImpl.java", datas);
		
		//生成service接口
		String service_outpath;
		String servicepackage_topath=service_package.replaceAll("\\.", fileseparator_regex);
		if(basepath.endsWith(File.separator)){
			service_outpath=basepath+servicepackage_topath;
		}else {
			service_outpath=basepath+File.separator+servicepackage_topath;
		}
		generateCode(properties.getProperty("freemarker.service.filename"), service_outpath, entityName+"Service.java", datas);
		
		//生成serviceimpl
		String serviceimpl_outpath;
		String serviceimplpackage_topath=serviceimpl_package.replaceAll("\\.", fileseparator_regex);
		if(basepath.endsWith(File.separator)){
			serviceimpl_outpath=basepath+serviceimplpackage_topath;
		}else {
			serviceimpl_outpath=basepath+File.separator+serviceimplpackage_topath;
		}
		generateCode(properties.getProperty("freemarker.serviceimpl.filename"), serviceimpl_outpath, entityName+"ServiceImpl.java", datas);
		
		//生成controller
		String controller_outpath;
		String controllerpackage_topath=controller_package.replaceAll("\\.", fileseparator_regex);
		if(basepath.endsWith(File.separator)){
			controller_outpath=basepath+controllerpackage_topath;
		}else {
			controller_outpath=basepath+File.separator+controllerpackage_topath;
		}
		generateCode(properties.getProperty("freemarker.controller.filename"), controller_outpath, entityName+"Controller.java", datas);
		
	}
	
	
	
	/**
	 * 生成实体类的源文件(.java)
	 * @param entityName 实体类名称，如Role
	 * @param entity_package 该实体类所在的包名,如com.easy.role.entity
	 * @param tableName 实体类对应的数据表名
	 * */
	public static void execute_generateEntityClass(String entityName,String entity_package,String tableName){
		Map<String, String> datas=createEntityDataMap(entityName, entity_package, tableName);
		String entity_outpath=null;
		String fileseparator_regex=getFileseparatorRegex();
		String entitypackage_topath=entity_package.replaceAll("\\.", fileseparator_regex);
		if(basepath.endsWith(File.separator)){
			entity_outpath=basepath+entitypackage_topath;
		}else {
			entity_outpath=basepath+File.separator+entitypackage_topath;
		}
		generateCode(properties.getProperty("freemarker.entity.filename"), entity_outpath, entityName+".java", datas);
		
	}
	
	/**
	 * 生成mapper.xml
	 * * @param entityName 实体类名称，如Role
	 * @param entityTableName 实体类对应的表名
	 * @param pkname 该表的主键字段名
	 * @param dao_package 该实体类对应的dao接口所在的包名,如com.easy.role.dao
	 * */
	public static void execute_generateMapperXml(String entityName,String entityTableName,String pkname,String dao_package){
		Map<String, String> datas=createMapperXmlDataMap(entityName, entityTableName, pkname, dao_package);
		generateCode(properties.getProperty("freemarker.mapper.filename"), properties.getProperty("mapper.xml.realpath"), entityName+"Mapper.xml", datas);
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
		execute_generateEntityClass(entityName, entity_package, tableName);
		execute_generateCode(entityName, entity_package, dao_package, daoimpl_package, service_package, serviceimpl_package, controller_package);
		execute_generateMapperXml(entityName, tableName, pkname, dao_package);
	}
	
	
	
	
	
	public static void main(String[] args) {
		//使用方法：只需配置好代码生成框架中的code.properties和jdbc.properties即可
		//可以通过generateAll()生成所以文件，也可以通过分别调用生成相应部分

		String entityName="Role";
		String entityPackage="com.easy.role.entity";
		String dao_package="com.easy.role.dao";
		String daoimpl_package="com.easy.role.dao.impl";
		String service_package="com.easy.role.service";
		String serviceimpl_package="com.easy.role.service.impl";
		String controller_package="com.easy.role.controller";
		String tableName="role";
		String pkname="id";
		
//		execute_generateCode(entityName,entityPackage,dao_package,daoimpl_package,service_package,serviceimpl_package,controller_package);		
//		execute_generateMapperXml(entityName,tableName,pkname,dao_package);
//		execute_generateEntityClass(entityName,entityPackage,tableName);
//		
		generateAll(entityName, entityPackage, dao_package, daoimpl_package, service_package, serviceimpl_package, controller_package, tableName, pkname);
		
		
	
	}

}
