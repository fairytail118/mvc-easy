/**
 * cxybj.com Copyright (c) 2012-2013 All Rights Reserved.
 */
package codeworker.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;


/**
 * 
 * @author wy
 * @version v 0.1 2013-6-1 下午10:41:38 wy Exp $
 */
public class MysqlProvider extends JdbcProvider {

    /** 日志对象 */
    private final static Logger LOG = Logger.getLogger(JdbcUtil.class);

    /**
     * @see com.xdcxy.generate.db.DatabaseProvider#getTableComment(java.lang.String)
     */
    @Override
    public String getTableComment(String tableName) {

        String comment = super.getTableComment(tableName);
        if (comment != null) {
            return comment;
        }

        Connection conn = JdbcUtil.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("show table status like ?");
            ps.setString(1, tableName);
            rs = ps.executeQuery();
            while (rs.next()) {
                comment = rs.getString("Comment");
                break;
            }
        }
        catch (SQLException e) {
            LOG.error("读取表注释出错", e);
            throw new RuntimeException("读取表注释出错");
        }
        finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(ps);
            JdbcUtil.close(conn);
        }

        return comment;
    }

}
