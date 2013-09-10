/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.mybatis;

import java.sql.Connection;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;


/**
 * Mysql的分页
 * 
 * @author wy
 * @version v 0.1 2013-2-17 下午9:38:34 wy Exp $
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class MysqlPageInterceptor extends AbstractPageInterceptor {

    /**
     * 获取Mysql的分页sql
     * 
     * @param sql
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public String getPageSql(String sql, int offset, int limit) {
        StringBuilder pageSQL = new StringBuilder();
        pageSQL.append(sql.trim());
        pageSQL.append(" LIMIT ").append(offset).append(",").append(limit);
        return pageSQL.toString();
    }

}
