/**
 * cxybj.com Copyright (c) 2012-2013 All Rights Reserved.
 */
package codeworker.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import codeworker.config.ConfigPropertiesUtil;
import codeworker.db.model.Column;
import codeworker.db.model.PrimaryType;
import codeworker.db.model.Table;


/**
 * 
 * @author wy
 * @version v 0.1 2013-6-2 下午12:42:02 wy Exp $
 */
public class TableUtil {


    /**
     * 获取表的信息
     * 
     * @param tableName
     * @return
     */
    public static Table getTable(String tableName) {
        String driverClass = ConfigPropertiesUtil.get("db.provider");
        Object provider = null;
        try {
            provider = Class.forName(driverClass).newInstance();
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException("找不到类" + driverClass);
        }
        catch (Exception e) {
            throw new RuntimeException("初始化" + driverClass + "异常", e);
        }

        if (!(provider instanceof DatabaseProvider)) {
            throw new RuntimeException("" + driverClass + "类型不正确");
        }

        DatabaseProvider databaseProvider = (DatabaseProvider) provider;
        String tableComment = databaseProvider.getTableComment(tableName);
        List<Column> columnList = databaseProvider.getTableFields(tableName);
        List<String> primaryList = databaseProvider.getPrimaryColumns(tableName);  
        Table table = new Table();
        table.setName(tableName);
        table.setDesc(tableComment);
        if (primaryList == null || primaryList.isEmpty()) {
            table.setPrimaryType(PrimaryType.NONE);
        } else if (primaryList.size() == 1) {
            table.setPrimaryType(PrimaryType.ONE);
        } else {
            table.setPrimaryType(PrimaryType.MANY);
        }
        table.setPrimaryList(primaryList);   
        for (Column column : columnList) {
            column.setPrimary(primaryList.contains(column.getColumn()));
        }

        table.setColumnList(columnList);

        return table;
    }
    
    /**
	 * @param table
	 * @return 指定表的列名和对应的java类型
	 */
	public static Map<String, String> getTableCloums(Table table){		
		List<Column> columns=table.getColumnList();
		Map<String, String> map=new HashMap<String, String>(columns.size());
		for(Column column : columns){
			map.put(column.getColumn(), column.getTypeClass());
		}
		return map;
		
	}
    
    public static void main(String[] args) {
    	Table table=getTable("role");
    	String comment=new MysqlProvider().getTableComment("role");
    	List<Column> columns=table.getColumnList();
    	System.out.println("主键:"+table.getPrimaryList()+",表注释:"+comment);
    	for(Column column : columns){
    		System.out.println("字段名:"+column.getColumn()+",注释："+column.getComment()+",长度:"+column.getLength()+",Java类型："+column.getTypeClass()+",是否主键:"+column.isPrimary()+",是否非空:"+column.getIsNullable());
    	}
    }

}
