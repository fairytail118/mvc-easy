package codeworker;

/**
 * 数据库类型与Java类型转换的简单工厂
 * */
public class DbTypeToJavaTypeSimpleFactory {

	/**
	 * 表示数据库的种类
	 * */
	public enum DbEnum {
		MYSQL, ORACLE, SQLSERVER
	}

	private static DbEnum currentDb = DbEnum.MYSQL;// 当前使用的数据库

	/**
	 * 设置当前使用的数据库
	 * */
	public synchronized static void setCurrentDb(DbEnum dbEnum) {
		currentDb = dbEnum;
	}

	/**
	 * 数据库类型转换成Java类型的完整字符串，包含包路径
	 * */
	public static String dbTypeStringToJavaTypeString(String dbtype) {
		String javatypeString=null;
		switch (currentDb) {
			case MYSQL:
				if(dbtype.equalsIgnoreCase("BIGINT")){
					javatypeString="Long";
				}else if(dbtype.equalsIgnoreCase("DATETIME")){
					javatypeString="java.util.Date";					
				}else if(dbtype.equalsIgnoreCase("VARCHAR")){
					javatypeString="String";					
				}else if(dbtype.equalsIgnoreCase("TINYINT")){
					javatypeString="Boolean";					
				}else if(dbtype.equalsIgnoreCase("DATETIME")){
					javatypeString="java.util.Date";					
				}else {
					javatypeString="其他";//根据实际情况在此处调整
				}
				
				break;
	
			case ORACLE:
	
				break;
	
			case SQLSERVER:
	
				break;
	
			default:

		}
		return javatypeString;
	}

	
}
