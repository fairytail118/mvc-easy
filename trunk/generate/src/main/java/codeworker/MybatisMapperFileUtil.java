package codeworker;

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
		Table table=TableUtil.getTable(tableName);
		List<Column> columns=table.getColumnList();
		Map<String, String> map=new HashMap<String, String>(columns.size());
		for(Column column : columns){
			map.put(column.getColumn(), column.getTypeClass());
		}
		return map;
		
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
		sql.append("UPDATE ").append(tableName).append(" SET \n<set>\n");
		Iterator<String> cloumSet=cloums.keySet().iterator();
		String cloum=null;
		while(cloumSet.hasNext()){
			cloum=cloumSet.next();
			if(cloum.equalsIgnoreCase(pkname)){//不包含主键列
				continue;
			}
			sql.append("<if test=\"").append(cloum).append("!=null\">").append(cloum).append(" = #{").append(cloum).append("},</if>");
			
		}
		sql.append(" WHERE ").append(pkname).append(" = #{").append(pkname).append("}");
		return sql.toString();
		
	}
	
	public static String getMybatisUpdateSql(Map<String, String> cloums,Table table){
		StringBuilder sql=new StringBuilder(100);
		sql.append("UPDATE ").append(table.getName()).append(" SET \n<set>\n");
		Iterator<String> cloumSet=cloums.keySet().iterator();
		String cloum=null;
		boolean skip=false;
		while(cloumSet.hasNext()){
			cloum=cloumSet.next();
			for(String pk : table.getPrimaryList()){
				if(cloum.equalsIgnoreCase(pk)){
					skip=true;
					break;
				}
			}
			if(skip){
				continue;
			}
			sql.append("<if test=\"").append(cloum).append("!=null\">").append(cloum).append(" = #{").append(cloum).append("},</if>");
		}
		sql.append(" WHERE ");
		int index=0;
		for(String pk : table.getPrimaryList()){
			sql.append(pk).append(" = #{").append(pk).append("} ");
			index++;
			if(index!=table.getPrimaryList().size()){
				sql.append(" AND ");
			}
		}
		return sql.toString();
		
	}
	
	public static String getMybatisDeleteSql(String tableName,String pkname){
		StringBuilder sql=new StringBuilder(100);
		sql.append("DELETE FROM ").append(tableName).append(" WHERE ").append(pkname).append(" in \n");
		sql.append("<foreach collection=\"array\" item=\"id\" open=\"(\" separator=\",\" close=\")\">#{id}</foreach>");
		return sql.toString();
	}
	
	public static String getMybatisDeleteSql(Table table){
		StringBuilder sql=new StringBuilder(100);
		sql.append("DELETE FROM ").append(table.getName()).append(" WHERE ");
		int index=0;
		for(String pk : table.getPrimaryList()){
			sql.append(pk).append(" = #{").append(pk).append("} ");
			index++;
			if(index!=table.getPrimaryList().size()){
				sql.append(" AND ");
			}
		}
		return sql.toString();
	}
	
	public static String getMybatisSelectSql(String tableName,String pkname){
		StringBuilder sql=new StringBuilder(100);
		sql.append("SELECT <include refid=\"columns\"/> FROM ").append(tableName).append(" WHERE ").append(pkname).append(" = #{").append(pkname).append("}");
		return sql.toString();
	}
	
	public static String getMybatisSelectSql(Table table){
		StringBuilder sql=new StringBuilder(100);
		sql.append("SELECT <include refid=\"columns\"/> FROM ").append(table.getName()).append(" WHERE ");
		int index=0;
		for(String pk : table.getPrimaryList()){
			sql.append(pk).append(" = #{").append(pk).append("} ");
			index++;
			if(index!=table.getPrimaryList().size()){
				sql.append(" AND ");
			}
		}
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
				sql.append("<id property=\"").append("id").append("\" column=\"").append(pkname).append("\" />\n");
			}else{
				sql.append("<result property=\"").append(cloum).append("\" column=\"").append(cloum).append("\" />\n");
			
			}
		}
		return sql.toString();
		
	}
	
	public static String getResultMap(Map<String, String> cloums,Table table){		
		StringBuilder sql=new StringBuilder(150);	
		for(String pk : table.getPrimaryList()){
			sql.append("\t<id property=\"").append(pk).append("\" column=\"").append(pk).append("\" />\n");
		}
		Iterator<String> cloumSet=cloums.keySet().iterator();
		String cloum=null;
		while(cloumSet.hasNext()){
			cloum=cloumSet.next();			
			sql.append("\t<result property=\"").append(cloum).append("\" column=\"").append(cloum).append("\" />\n");			
		}
		return sql.toString();
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> colum=getTableCloums("user");
		String table="user";
		String pkname="id";
//		System.out.println("insert sql:"+getMybatisInsertSql(colum, table, pkname));
		System.out.println("update sql:"+getMybatisUpdateSql(colum, table, pkname));
//		System.out.println("delete sql:"+getMybatisDeleteSql(table, pkname));
//		System.out.println("select sql:"+getMybatisSelectSql(table, pkname));
//		System.out.println("cloums:"+getCloumnStr(colum));
//		System.out.println("result map:"+getResultMap(colum, pkname));
		
	}

}
