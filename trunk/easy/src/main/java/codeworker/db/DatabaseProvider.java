/**
 * cxybj.com Copyright (c) 2012-2013 All Rights Reserved.
 */
package codeworker.db;

import java.util.List;
import codeworker.db.model.Column;

/**
 * 获取数据库的相关信息
 * 
 * @author wy
 * @version v 0.1 2013-6-1 下午10:31:35 wy Exp $
 */
public interface DatabaseProvider {

    /**
     * 获取数据库的表名注释
     * 
     * @param tableName
     * @return
     */
    public String getTableComment(String tableName);

    /**
     * 获取数据库的表字段
     * 
     * @param tableName
     * @return
     */
    public List<Column> getTableFields(String tableName);

    /**
     * 获取表的主键列表
     * 
     * @param tableName
     * @return
     */
    public List<String> getPrimaryColumns(String tableName);

   

}
