package codeworker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import codeworker.db.TableUtil;
import codeworker.db.model.Column;
import codeworker.db.model.Table;

/**
 * 生成mybatis的mapper xml文件中的相关sql语句工具类
 * */
public class MybatisMapperFileUtil {
	
	/**
	 * 适用于mysql
	 * @param tableName
	 * @return 指定表的列名和对应的java类型
	 */
	public static Map<String, String> getTableCloums(String tableName){
//		Properties jdbcProperties=new Properties();
//		Connection connection=null;
//		Statement statement=null;
//		ResultSet resultSet=null;
//		try {
//			jdbcProperties.load(JdbcTableMetedataReader.class.getResourceAsStream("jdbc.properties"));		
//			connection=DriverManager.getConnection(	jdbcProperties.getProperty("jdbc.url"), jdbcProperties.getProperty("jdbc.username"), jdbcProperties.getProperty("jdbc.password"));
//			statement=connection.createStatement();
//			resultSet=statement.executeQuery("select * from "+tableName+" limit 1");
//			ResultSetMetaData metaData=resultSet.getMetaData();
//			int cloum_count=metaData.getColumnCount();
//			Map<String, String> cloums=new HashMap<String, String>(cloum_count);
//			for(int i=0;i<cloum_count;i++){
//				cloums.put(metaData.getColumnName(i+1),metaData.getColumnTypeName(i+1));
//				//System.out.println("列名:"+metaData.getColumnName(i+1)+",列类型:"+metaData.getColumnTypeName(i+1));
//			}
//			close(connection, statement, resultSet);
//			return cloums;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}finally{
//			close(connection, statement, resultSet);
//		}
		
		Table table=TableUtil.getTable(tableName);
		List<Column> columns=table.getColumnList();
		Map<String, String> map=new HashMap<String, String>(columns.size());
		for(Column column : columns){
			map.put(column.getColumn(), column.getTypeClass());
		}
		return map;
		
	}
	
	private static void close(Connection connection,Statement statement,ResultSet resultSet){
		try{
			if(resultSet!=null && !resultSet.isClosed()){
				resultSet.close();
			}
			if(statement!=null && !statement.isClosed()){
				statement.close();
			}
			if(connection!=null && !connection.isClosed()){
				connection.close();
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static String getMybatisInsertSql(Map<String, String> cloums,String tableName,String pkname){
		StringBuilder sql=new StringBuilder(100);
		sql.append("INSERT INTO ").append(tableName).append(" ( ");
		Iterator<String> cloumSet=cloums.keySet().iterator();
		String cloum=null;
		while(cloumSet.hasNext()){
			cloum=cloumSet.next();
			if(cloum.equalsIgnoreCase(pkname)){//不包含主键列
				continue;
			}
			sql.append(cloum);
			if(cloumSet.hasNext()){
				sql.append(",");
			}
		}
		sql.append(" ) VALUES ( ");
		
		cloumSet=cloums.keySet().iterator();		
		while(cloumSet.hasNext()){
			cloum=cloumSet.next();
			if(cloum.equalsIgnoreCase(pkname)){//不包含主键列
				continue;
			}
			sql.append("#{").append(cloum).append("}");
			if(cloumSet.hasNext()){
				sql.append(",");
			}
		}
		sql.append(" )");
		return sql.toString();
	}
	
	public static String getMybatisUpdateSql(Map<String, String> cloums,String tableName,String pkname){
		StringBuilder sql=new StringBuilder(100);
		sql.append("UPDATE ").append(tableName).append(" SET ");
		Iterator<String> cloumSet=cloums.keySet().iterator();
		String cloum=null;
		while(cloumSet.hasNext()){
			cloum=cloumSet.next();
			if(cloum.equalsIgnoreCase(pkname)){//不包含主键列
				continue;
			}
			sql.append(cloum).append(" = #{").append(cloum).append("}");
			if(cloumSet.hasNext()){
				sql.append(",");
			}
		}
		sql.append(" WHERE ").append(pkname).append(" = #{").append(pkname).append("}");
		return sql.toString();
		
	}
	
	public static String getMybatisDeleteSql(String tableName,String pkname){
		StringBuilder sql=new StringBuilder(100);
		sql.append("DELETE FROM ").append(tableName).append(" WHERE ").append(pkname).append(" in \n");
		sql.append("<foreach collection=\"array\" item=\"id\" open=\"(\" separator=\",\" close=\")\">#{id}</foreach>");
		return sql.toString();
	}
	
	public static String getMybatisSelectSql(String tableName,String pkname){
		StringBuilder sql=new StringBuilder(100);
		sql.append("SELECT <include refid=\"columns\"/> FROM ").append(tableName).append(" WHERE ").append(pkname).append(" = #{").append(pkname).append("}");
		return sql.toString();
	}
	
	public static String getCloumnStr(Map<String, String> cloums){
		StringBuilder sql=new StringBuilder(100);
		Iterator<String> cloumSet=cloums.keySet().iterator();
		String cloum=null;
		while(cloumSet.hasNext()){
			cloum=cloumSet.next();
			sql.append(cloum);
			if(cloumSet.hasNext()){
				sql.append(",");
			}
		}
		return sql.toString();
		
	}
	
	public static String getResultMap(Map<String, String> cloums,String pkname){		
		StringBuilder sql=new StringBuilder(150);
		Iterator<String> cloumSet=cloums.keySet().iterator();
		String cloum=null;
		while(cloumSet.hasNext()){
			cloum=cloumSet.next();
			if(cloum.equalsIgnoreCase(pkname)){
				sql.append("<id property=\"").append(pkname).append("\" column=\"").append(pkname).append("\" />\n");
			}else{
				sql.append("<result property=\"").append(cloum).append("\" column=\"").append(cloum).append("\" />\n");
			
			}
		}
		return sql.toString();
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> colum=getTableCloums("admin");
		String table="admin";
		String pkname="id";
//		System.out.println("insert sql:"+getMybatisInsertSql(colum, table, pkname));
//		System.out.println("update sql:"+getMybatisUpdateSql(colum, table, pkname));
//		System.out.println("delete sql:"+getMybatisDeleteSql(table, pkname));
//		System.out.println("select sql:"+getMybatisSelectSql(table, pkname));
//		System.out.println("cloums:"+getCloumnStr(colum));
//		System.out.println("result map:"+getResultMap(colum, pkname));
		
	}

}
