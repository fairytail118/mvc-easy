/**
 * Copyright (c) 2012-2013 All Rights Reserved.
 */
package com.easy.core.mybatis;

import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 分页抽象
 * 
 * @author wy
 * @version v 0.1 2013-2-17 下午9:38:34 wy Exp $
 */
public abstract class AbstractPageInterceptor implements Interceptor {

    /** 日志对象 */
    protected final static Logger LOG = LoggerFactory.getLogger(AbstractPageInterceptor.class);

    /**
     * 
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public final Object intercept(Invocation invocation) throws Throwable {
        
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(statementHandler);

        RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");

        //未设置分页
        if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
            return invocation.proceed();
        }

        String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");

        String pageSQL = getPageSql(sql, rowBounds.getOffset(), rowBounds.getLimit());

        LOG.info("生成的分页SQL为:{}", pageSQL);

        //设置新的分页sql
        metaStatementHandler.setValue("delegate.boundSql.sql", pageSQL);

        metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
        metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);

        return invocation.proceed();
    }

    /**
     * 获取分页sql
     * 
     * @param sql
     * @param offset
     * @param limit
     * @return
     */
    public abstract String getPageSql(String sql, int offset, int limit);

    /**
     * 
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {

    }

}
