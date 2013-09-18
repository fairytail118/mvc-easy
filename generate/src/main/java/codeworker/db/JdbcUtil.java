/**
 * cxybj.com Copyright (c) 2012-2013 All Rights Reserved.
 */
package codeworker.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

import codeworker.config.ConfigPropertiesUtil;

/**
 * 
 * @author wy
 * @version v 0.1 2013-6-2 上午12:03:59 wy Exp $
 */
public class JdbcUtil {

    /** 日志对象 */
    private final static Logger LOG = Logger.getLogger(JdbcUtil.class);

    /**
     * 获取数据库连接
     * 
     * @param driverClass
     * @param jdbcUrl
     * @param user
     * @param pwd
     * @return
     */
    public static Connection getConnection(String driverClass, String jdbcUrl, String user,
                                           String pwd) {

        try {
            Class.forName(driverClass);
            return DriverManager.getConnection(jdbcUrl, user, pwd);
        }
        catch (Exception e) {
            throw new RuntimeException("创建数据库连接失败", e);
        }
    }

    /**
     * 获取mysql的数据库链接
     * 
     * @return
     */
    public static Connection getConnection() {
        return getConnection(ConfigPropertiesUtil.get("jdbc.driverClassName"),
            ConfigPropertiesUtil.get("jdbc.url"), ConfigPropertiesUtil.get("jdbc.username"),
            ConfigPropertiesUtil.get("jdbc.password"));
    }

    /**
     * 关闭
     * 
     * @param conn
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            }
            catch (SQLException e) {
                LOG.error("关闭数据库Connection异常", e);
            }
        }
    }

    /**
     * 关闭
     * 
     * @param rs
     */
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            }
            catch (SQLException e) {
                LOG.error("关闭数据库ResultSet异常", e);
            }
        }
    }

    /**
     * 关闭
     * 
     * @param conn
     */
    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            }
            catch (SQLException e) {
                LOG.error("关闭数据库Statement异常", e);
            }
        }
    }

}
