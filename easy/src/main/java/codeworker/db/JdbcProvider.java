/**
 * cxybj.com Copyright (c) 2012-2013 All Rights Reserved.
 */
package codeworker.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.BooleanUtils;
import org.apache.log4j.Logger;
import codeworker.db.model.Column;


/**
 * 
 * @author wy
 * @version v 0.1 2013-6-2 上午9:46:15 wy Exp $
 */
public class JdbcProvider implements DatabaseProvider {

    /** 日志对象 */
    protected final Logger LOG = Logger.getLogger(getClass());

    /**
     * @see com.xdcxy.generate.db.DatabaseProvider#getTableComment(java.lang.String)
     */
    @Override
    public String getTableComment(String tableName) {
        Connection conn = JdbcUtil.getConnection();
        ResultSet rs = null;
        String comment = null;
        try {
            DatabaseMetaData meta = conn.getMetaData();
            rs = meta.getTables(null, null, tableName, new String[] { "VIEW", "TABLE" });
            while (rs.next()) {
                comment = rs.getString("REMARKS");
                break;
            }
        }
        catch (SQLException e) {
            LOG.error("获取表注释异常", e);
            throw new RuntimeException("获取表注释异常");
        }
        finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(conn);
        }
        return comment;
    }

    /**
     * @see com.xdcxy.generate.db.DatabaseProvider#getTableFields(java.lang.String)
     */
    @Override
    public List<Column> getTableFields(String tableName) {

        Connection conn = JdbcUtil.getConnection();
        ResultSet rs = null;
        List<Column> list = new ArrayList<Column>();
        try {
            DatabaseMetaData meta = conn.getMetaData();
            rs = meta.getColumns(null, null, tableName, null);
            while (rs.next()) {
                String name = rs.getString("COLUMN_NAME");
                int size = rs.getInt("COLUMN_SIZE");
                String comment = rs.getString("REMARKS");
                boolean autoincrement = BooleanUtils.toBoolean(rs.getString("IS_AUTOINCREMENT"));
                String type = rs.getString("TYPE_NAME");
                String clazz = DbTypeToJavaTypeSimpleFactory.dbTypeStringToJavaTypeString(type);
                Column column = new Column();
                column.setComment(comment);
                column.setColumn(name);
                column.setLength(size);
                column.setTypeClass(clazz);
                column.setAutoincrement(autoincrement);
                int nullable=rs.getInt("NULLABLE");
                if(nullable==DatabaseMetaData.columnNoNulls){
                	column.setNullable(false);
                }else {
					column.setNullable(true);
				}
                list.add(column);
            }
        }
        catch (SQLException e) {
            LOG.error("获取表的字段出错", e);
            throw new RuntimeException("获取表的字段出错");
        }
        finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(conn);
        }
        return list;
    }

    /**
     * @see com.xdcxy.generate.db.DatabaseProvider#getPrimaryColumns(java.lang.String)
     */
    @Override
    public List<String> getPrimaryColumns(String tableName) {
        Connection conn = JdbcUtil.getConnection();
        ResultSet rs = null;
        List<String> list = new ArrayList<String>();
        try {
            DatabaseMetaData meta = conn.getMetaData();
            rs = meta.getPrimaryKeys(null, null, tableName);
            while (rs.next()) {
                String column = rs.getString("COLUMN_NAME");
                list.add(column);
            }
        }
        catch (SQLException e) {
            LOG.error("获取表的主键出错", e);
            throw new RuntimeException("获取表的主键出错");
        }
        finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(conn);
        }
        return list;
    }

}
