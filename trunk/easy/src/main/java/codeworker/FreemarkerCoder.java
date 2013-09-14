package codeworker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerCoder {
	
	private FreemarkerCoder(){}
	private static Configuration configuration=new Configuration();
	private static Properties properties=new Properties();//代码生成相关配置
	
	static{
		try{					
			properties.load(FreemarkerCoder.class.getResourceAsStream("code.properties"));
			configuration.setDirectoryForTemplateLoading(new File(properties.getProperty("freemarker.templateDirectory")));
			configuration.setEncoding(Locale.getDefault(),properties.getProperty("freemarker.encoding"));
		}catch (Exception e) {
			System.err.println("freemarker 初始化异常:"+e);
		}
	}
	
	private static final String basepath=properties.getProperty("project.src.realpath");

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
	
	//生成Java文件的相关数据变量
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
	
	/**
	 * 根据code.properties配置生成相关文件目录及文件
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
	
	
	//生成mapper.xml文件的相关数据变量
	private static Map<String, String> createMapperXmlDataMap(String entityName,String entityTableName,String pkname,String dao_package){
		Map<String, String> datas=new HashMap<String, String>();
		datas.put("dao_package", dao_package);
		datas.put("entity", entityName);
		String lower_entity=entityName.substring(0, 1).toLowerCase()+entityName.substring(1);//实体类名的第一个字符给为小写
		datas.put("lower_entity", lower_entity);
		datas.put("pkname", pkname);
		Map<String, String> colum=JdbcTableMetedataReader.getTableCloums("admin");
		datas.put("insert_sql", JdbcTableMetedataReader.getMybatisInsertSql(colum, entityTableName, pkname));
		datas.put("update_sql", JdbcTableMetedataReader.getMybatisUpdateSql(colum, entityTableName, pkname));
		datas.put("delete_sql", JdbcTableMetedataReader.getMybatisDeleteSql(entityTableName, pkname));
		datas.put("select_sql", JdbcTableMetedataReader.getMybatisSelectSql(entityTableName, pkname));
		datas.put("cloumns", JdbcTableMetedataReader.getCloumnStr(colum));
		datas.put("resultMap", JdbcTableMetedataReader.getResultMap(colum, pkname));
		return datas;
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
	
	public static void main(String[] args) {
		//示例，当前项目中Role相关的类及mapper.xml文件都是有下面的方法生成的，你们可以试下，只需配置好code.properties即可
		//需要自己先建好相应的实体类
		execute_generateCode("Role", "com.easy.role.entity", "com.easy.role.dao", "com.easy.role.dao.impl", "com.easy.role.service", "com.easy.role.service.impl", "com.easy.role.controller");
		
		execute_generateMapperXml("Role", "role", "id", "com.easy.role.dao");
		//System.out.println(System.getProperties().getProperty("os.name"));
		//System.out.println("com.easy".replaceAll("\\.", "\\"+"\\"));
	}

}
